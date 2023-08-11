package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.services.MerchantService;
import com.MyPay.MyPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080/")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping(value = "/merchants")
    public List<Merchant> getAllMerchants(){

        new ResponseEntity<>(HttpStatus.OK);
        return merchantService.findAllMerchants();

    }

    @GetMapping(value = "/merchants/{id}")
    public Optional<Merchant> getMerchantById(@PathVariable Integer id){

        Optional<Merchant> merchant = merchantService.findMerchantById(id);
        return merchant;

    }

    @PostMapping(value = "/registermerchant")
    public ResponseEntity<?> registerUser(@RequestBody Merchant merchant) {
        ResponseEntity<?> registrationResponse = merchantService.registerMerchant(merchant.getPassword(), merchant.getPhonenumber(),merchant.getMerchantaddress(),merchant.getMerchantname());
        return registrationResponse;
    }

    @PostMapping(value = "/loginmerchant")
    public ResponseEntity<?> loginUser(@RequestBody Merchant body) {
        ResponseEntity<?> loginResponse =  merchantService.loginMerchant(body.getPhonenumber(), body.getPassword());
        return loginResponse;
    }

    @PutMapping(value = "/merchants/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable Integer id, @RequestBody Merchant merchant) {
        ResponseEntity<?> updateResponse = merchantService.updateMerchantInformation(id, merchant.getMerchantaddress(), merchant.getMerchantname(), merchant.getPassword(),merchant.getPhonenumber() );
        return updateResponse;
    }




}
