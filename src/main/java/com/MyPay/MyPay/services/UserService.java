package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAllUsers();

    Optional<User> findUserById(Integer integer);

    ResponseEntity<?> validateUserPhoneNumber(Integer phonenumber);

    ResponseEntity<?> registerUser(Integer phonenumber , String useraddress, String username );








}
