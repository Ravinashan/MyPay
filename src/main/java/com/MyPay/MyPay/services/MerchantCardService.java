package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.MerchantCard;
import com.MyPay.MyPay.entity.UserCard;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MerchantCardService {

    List<MerchantCard> getMerchantCardsByMerchantId(Integer merchantid);

    ResponseEntity<?> registerMerchantCard(MerchantCard merchantCard , int id);

    ResponseEntity<?> updateMerchantCardDetails(int cardId, MerchantCard updatedMerchantCard);

}
