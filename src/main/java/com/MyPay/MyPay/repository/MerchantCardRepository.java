package com.MyPay.MyPay.repository;


import com.MyPay.MyPay.entity.MerchantCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchantCardRepository extends JpaRepository<MerchantCard,Integer> {

    @Query(value = "SELECT * FROM mypaydatabase.merchantcard where merchantid = ?1",nativeQuery = true)
    List<MerchantCard> getMerchantCardsByMerchantId(Integer merchantid);



}
