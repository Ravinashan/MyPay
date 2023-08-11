package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.Counter;
import com.MyPay.MyPay.entity.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface CounterRepository extends JpaRepository<Counter,Integer> {


    @Query(value ="SELECT * FROM mypaydatabase.counter where shopId = ?1", nativeQuery = true)
    Optional<Counter> getCounterByShopId(Integer shopId);

    @Query(value = "SELECT shopid FROM mypaydatabase.counter where counterid = ?1", nativeQuery = true)
    Integer getShopIdByCounterId(Integer counterid);

    @Query(value = "SELECT * FROM mypaydatabase.counter where counterid = ?1", nativeQuery = true)
    Counter getCounterById(Integer counterid);

    @Transactional
    @Modifying
    @Query("UPDATE Counter c SET c.wallet = :newWalletValue WHERE c.counterid = :counterId")
    void updateWalletValue(int counterId, float newWalletValue);




}
