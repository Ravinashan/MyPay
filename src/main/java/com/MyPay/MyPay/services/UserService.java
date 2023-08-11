package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAllUsers();

    Optional<User> findUserById(Integer integer);

    ResponseEntity<?> validateUserPhoneNumber(Integer phonenumber);

    ResponseEntity<?> registerUser(String password, Integer phonenumber , String useraddress, String username  );

    ResponseEntity<?> loginUser(Integer phonenumber , String password);

    ResponseEntity<?> updateUserInformation(Integer userId, String useraddress, String username , String password , Integer phonenumber );











}
