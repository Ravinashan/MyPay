package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.entity.UserCard;
import com.MyPay.MyPay.repository.UserCardRepository;
import com.MyPay.MyPay.utility.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCardServiceImp implements UserCardService {


    private final UserCardRepository userCardRepository;
    private final UserService  userService;
    private final  EncryptionUtil encryptionUtil;


    public UserCardServiceImp(UserCardRepository userCardRepository, UserService userService, EncryptionUtil encryptionUtil){

        this.userCardRepository = userCardRepository;
        this.userService = userService;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public List<UserCard> getUserCardsByUserId(Integer userid){

        List<UserCard> cards = userCardRepository.getUserCardsByUserId(userid);

        for (UserCard card : cards) {
            String decryptedCardNumber = encryptionUtil.decrypt((card.getCardnumber()));
            String decryptedCvv = encryptionUtil.decrypt((card.getCvv()));

            // Convert decrypted strings back to integers
            card.setCardnumber((decryptedCardNumber));
            card.setCvv((decryptedCvv));
        }


        return cards;
    }

    @Override
    public Optional<UserCard> findUserCardById(Integer usercardid){
        Optional<UserCard> userCardOptional = userCardRepository.findById(usercardid);

        userCardOptional.ifPresent(userCard -> {
            String decryptedCardNumber = encryptionUtil.decrypt(userCard.getCardnumber());
            String decryptedCvv = encryptionUtil.decrypt(userCard.getCvv());

            userCard.setCardnumber(decryptedCardNumber);
            userCard.setCvv(decryptedCvv);
        });

        return userCardOptional;
    }


    @Override
    public ResponseEntity<?> registerUserCard(UserCard userCard, int id) {
        UserCard newUserCard = new UserCard();

        // Encrypt and store as strings
        String encryptedCardNumber = encryptionUtil.encrypt((userCard.getCardnumber()));
        String encryptedCvv = encryptionUtil.encrypt((userCard.getCvv()));

        newUserCard.setCardnumber(encryptedCardNumber);
        newUserCard.setCardtype(userCard.getCardtype());
        newUserCard.setCvv(encryptedCvv);
        newUserCard.setExpirydate(userCard.getExpirydate());

        Optional<User> u = userService.findUserById(id);
        if (u.isPresent()) {
            newUserCard.setUser(u.get());
        }

        userCardRepository.save(newUserCard);
        return ResponseEntity.ok().body("UserCard is registered successfully");
    }


    @Override
    public ResponseEntity<?> updateUserCardDetails(int cardId, UserCard updatedUserCard) {
        Optional<UserCard> userCardOptional = userCardRepository.findById(cardId);

        if (userCardOptional.isPresent()) {
            UserCard existingUserCard = userCardOptional.get();

            // Encrypt and store as strings
            String encryptedCardNumber = encryptionUtil.encrypt(updatedUserCard.getCardnumber());
            String encryptedCvv = encryptionUtil.encrypt(updatedUserCard.getCvv());

            existingUserCard.setCardnumber(encryptedCardNumber);
            existingUserCard.setCardtype(updatedUserCard.getCardtype());
            existingUserCard.setCvv(encryptedCvv);
            existingUserCard.setExpirydate(updatedUserCard.getExpirydate());

            userCardRepository.save(existingUserCard);
            return ResponseEntity.ok().body("User card details updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
