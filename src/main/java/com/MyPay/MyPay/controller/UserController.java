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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        ResponseEntity<?> registrationResponse = userService.registerUser(user.getPassword(), user.getPhonenumber(),user.getUseraddress(),user.getUsername());
        return registrationResponse;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody User body) {
        ResponseEntity<?> loginResponse =  userService.loginUser(body.getPhonenumber(), body.getPassword());
        return loginResponse;
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        ResponseEntity<?> updateResponse = userService.updateUserInformation(id, user.getUseraddress(), user.getUsername(), user.getPassword(),user.getPhonenumber() );
        return updateResponse;
    }





}
