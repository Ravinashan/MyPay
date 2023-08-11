package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant,Integer> {

    @Override
    Merchant getById(Integer integer);

    @Query(value = "SELECT * FROM mypaydatabase.merchant where phonenumber = ?1", nativeQuery = true)
    Merchant getMerchantByPhoneNumber(int  phonenumber);



}
