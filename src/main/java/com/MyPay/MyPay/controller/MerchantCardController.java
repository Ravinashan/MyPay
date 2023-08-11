package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.MerchantCard;
import com.MyPay.MyPay.entity.UserCard;
import com.MyPay.MyPay.services.MerchantCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080/")
public class MerchantCardController {

    @Autowired
    private MerchantCardService merchantCardService;

    @GetMapping(value = "/merchantcard/{id}")
    public List<MerchantCard> getMerchantCardsByMerchantId(@PathVariable Integer id){

        return merchantCardService.getMerchantCardsByMerchantId(id);

    }

    @PostMapping(value = "/{id}/registermerchantcard")
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantCard merchantCard , @PathVariable int id ) {
        return merchantCardService.registerMerchantCard(merchantCard,id);

    }

    @PutMapping(value = "/merchantcard/{cardId}")
    public ResponseEntity<?> updateMerchantCardDetails(@PathVariable int cardId, @RequestBody MerchantCard merchantCard) {
        ResponseEntity<?> updateResponse = merchantCardService.updateMerchantCardDetails(cardId, merchantCard);
        return updateResponse;
    }




}
