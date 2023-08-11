package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Integer> {

    @Query(value = "SELECT * FROM mypaydatabase.shop where shopid = ?1",nativeQuery = true )
    Shop getShopById(Integer shopid);

    @Query(value = "SELECT * FROM mypaydatabase.shop where shopname = ?1",nativeQuery = true)
    Shop getShopByName(String shopname);

    @Query(value ="SELECT * FROM mypaydatabase.shop where merchantid = ?1", nativeQuery = true)
    Optional<Shop> getShopByMerchantId(Integer merchantid);



}
