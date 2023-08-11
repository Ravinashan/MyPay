package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.UserCard;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserCardService {

    Optional<UserCard> findUserCardById(Integer usercardid);

    List<UserCard> getUserCardsByUserId(Integer userid);

    ResponseEntity<?> registerUserCard(UserCard userCard , int id);

    ResponseEntity<?> updateUserCardDetails(int cardId, UserCard updatedUserCard);



}
