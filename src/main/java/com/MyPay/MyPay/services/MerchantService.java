package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    public List<Merchant> findAllMerchants();

    Optional<Merchant> findMerchantById(Integer integer);

    ResponseEntity<?> validateMerchantPhoneNumber(Integer phonenumber);

    ResponseEntity<?> registerMerchant(String password, Integer phonenumber , String useraddress, String username );

    ResponseEntity<?> loginMerchant(Integer phonenumber , String password);

    ResponseEntity<?> updateMerchantInformation(Integer merchantId, String merchantaddress, String merchantname ,String password,Integer phonenumber);


}
