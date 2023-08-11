package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.MerchantCard;
import com.MyPay.MyPay.entity.UserCard;
import com.MyPay.MyPay.repository.MerchantCardRepository;
import com.MyPay.MyPay.utility.EncryptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantCardServiceImp implements MerchantCardService {


    private MerchantCardRepository merchantCardRepository;

    private MerchantService  merchantService;

    private final EncryptionUtil encryptionUtil;


    public MerchantCardServiceImp(MerchantCardRepository merchantCardRepository, MerchantService merchantService, EncryptionUtil encryptionUtil){

        this.merchantCardRepository = merchantCardRepository;
        this.merchantService = merchantService;


        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public List<MerchantCard> getMerchantCardsByMerchantId(Integer merchantid){

        List<MerchantCard> cards = merchantCardRepository.getMerchantCardsByMerchantId(merchantid);

        for (MerchantCard merchantCard : cards) {
            String decryptedMerchantNumber = encryptionUtil.decrypt((merchantCard.getCardnumber()));
            String decryptedCvv = encryptionUtil.decrypt((merchantCard.getCvv()));


            merchantCard.setCardnumber((decryptedMerchantNumber));
            merchantCard.setCvv((decryptedCvv));
        }

        return cards;


    }

    @Override
    public ResponseEntity<?> registerMerchantCard(MerchantCard merchantCard , int id){

        String encryptedCardNumber = encryptionUtil.encrypt((merchantCard.getCardnumber()));
        String encryptedCvv = encryptionUtil.encrypt((merchantCard.getCvv()));

        MerchantCard newMerchantCard = new MerchantCard();
        newMerchantCard.setCardnumber(encryptedCardNumber);
        newMerchantCard.setCardtype(merchantCard.getCardtype());
        newMerchantCard.setCvv(encryptedCvv);
        newMerchantCard.setExpirydate(merchantCard.getExpirydate());

        Optional<Merchant> m =  merchantService.findMerchantById(id);
        if(m.isPresent()){
            newMerchantCard.setMerchant(m.get());
        }

        merchantCardRepository.save(newMerchantCard);
        return ResponseEntity.ok().body("MerchantCard is registered successfully");

    }

    @Override
    public ResponseEntity<?> updateMerchantCardDetails(int cardId, MerchantCard updatedMerchantCard) {
        Optional<MerchantCard> merchantCardOptional = merchantCardRepository.findById(cardId);

        if (merchantCardOptional.isPresent()) {
            MerchantCard existingMerchantCard = merchantCardOptional.get();

            String encryptedCardNumber = encryptionUtil.encrypt(updatedMerchantCard.getCardnumber());
            String encryptedCvv = encryptionUtil.encrypt(updatedMerchantCard.getCvv());

            existingMerchantCard.setCardnumber(encryptedCardNumber);
            existingMerchantCard.setCardtype(updatedMerchantCard.getCardtype());
            existingMerchantCard.setCvv(encryptedCvv);
            existingMerchantCard.setExpirydate(updatedMerchantCard.getExpirydate());

            merchantCardRepository.save(existingMerchantCard);
            return ResponseEntity.ok().body("User card details updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
