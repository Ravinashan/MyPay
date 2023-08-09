package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){

        new ResponseEntity<>(HttpStatus.OK);
        return userService.findAllUsers();

    }

    @GetMapping(value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){

        Optional<User> user = userService.findUserById(id);
        return user;

    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestParam Integer phonenumber,
                                          @RequestParam String username,
                                          @RequestParam String useraddress) {
        ResponseEntity<?> registrationResponse = userService.registerUser(phonenumber, useraddress, username);
        return registrationResponse;
    }





}
