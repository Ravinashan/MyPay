package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.repository.MerchantRepository;
import com.MyPay.MyPay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImp implements MerchantService {


    private MerchantRepository merchantRepository;
    private otpService otpService;

    public MerchantServiceImp(MerchantRepository merchantRepository , otpService otpService){

        this.merchantRepository = merchantRepository;
        this.otpService = otpService;

    }

    @Override
    public List<Merchant> findAllMerchants(){

        List<Merchant> merchants = merchantRepository.findAll();
        return merchants;

    }

    @Override
    public Optional<Merchant> findMerchantById(Integer integer){

        Optional<Merchant> merchant = merchantRepository.findById(integer);
        return merchant;
    }

    @Override
    public ResponseEntity<?> validateMerchantPhoneNumber(Integer phonenumber) {

        List<Merchant> merchants = merchantRepository.findAll();

        for (Merchant merchant : merchants) {

            if (merchant.getPhonenumber() == phonenumber) {

                return ResponseEntity.badRequest().body("Phone number is already registered.");

            }
        }

        int otp = otpService.generateOtp();
        otpService.sendOtpToUser(otp, String.valueOf(phonenumber));
        return ResponseEntity.ok().body("Phone number is available for registration.");


    }

    @Override
    public ResponseEntity<?> registerMerchant( String password, Integer phonenumber  , String useraddress , String username ){

        ResponseEntity<?> a = validateMerchantPhoneNumber(phonenumber);
        if (a.getStatusCode() == HttpStatus.OK){

            Merchant newMerchant = new Merchant();
            newMerchant.setPassword(password);
            newMerchant.setPhonenumber(phonenumber);
            newMerchant.setMerchantname(username);
            newMerchant.setMerchantaddress(useraddress);

            merchantRepository.save(newMerchant);
            return ResponseEntity.ok().body("User registered sucessful");

        }

        else{

            return a;
        }

    }

    @Override
    public ResponseEntity<?> updateMerchantInformation(Integer merchantId, String merchantaddress, String merchantname , String password, Integer phonenumber ) {
        Optional<Merchant> merchantOptional = merchantRepository.findById(merchantId);

        if (merchantOptional.isPresent()) {
            Merchant merchant = merchantOptional.get();
            merchant.setMerchantaddress(merchantaddress);
            merchant.setMerchantname(merchantname);
            merchant.setPassword(password);
            merchant.setPhonenumber(phonenumber);

            merchantRepository.save(merchant);
            return ResponseEntity.ok().body("Merchant information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Override
    public ResponseEntity<?> loginMerchant(Integer phonenumber , String password){

        Merchant merchant = merchantRepository.getMerchantByPhoneNumber(phonenumber);

        if (merchant != null){
            if (merchant.getPassword().equals(password)){

                return new ResponseEntity<>(
                        new Merchant(
                                merchant.getMerchantid(),
                                merchant.getPhonenumber(),
                                merchant.getMerchantname(),
                                merchant.getMerchantaddress()
                        ),HttpStatus.OK);
            }
            else{

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        }

        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }


}
