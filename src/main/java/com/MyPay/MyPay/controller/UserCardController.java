package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.entity.UserCard;
import com.MyPay.MyPay.services.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:8080/")
public class UserCardController {

    @Autowired
    private UserCardService userCardService;

    @GetMapping(value = "/usercard/{id}")
    public List<UserCard> getUserCardsByUserId(@PathVariable Integer id){

        return userCardService.getUserCardsByUserId(id);

    }

    @PostMapping(value = "/{id}/registerusercard")
    public ResponseEntity<?> registerUser(@RequestBody UserCard userCard ,@PathVariable int id ) {
        return userCardService.registerUserCard(userCard,id);

    }

    @PutMapping(value = "/usercard/{cardId}")
    public ResponseEntity<?> updateUserCardDetails(@PathVariable int cardId, @RequestBody UserCard userCard) {
        ResponseEntity<?> updateResponse = userCardService.updateUserCardDetails(cardId, userCard);
        return updateResponse;
    }





}
