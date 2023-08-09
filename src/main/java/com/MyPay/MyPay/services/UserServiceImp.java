package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private otpService otpService;

    public UserServiceImp(UserRepository userRepository , otpService otpService){

        this.userRepository = userRepository;
        this.otpService = otpService;

    }

    @Override
    public List<User> findAllUsers(){

        List<User> users = userRepository.findAll();
        return users;

    }

    @Override
    public Optional<User> findUserById(Integer integer){

        Optional<User> user = userRepository.findById(integer);
        return user;
    }

    @Override
    public ResponseEntity<?> validateUserPhoneNumber(Integer phonenumber) {

        List<User> users = userRepository.findAll();

        for (User user : users) {

            if (user.getPhonenumber() == phonenumber) {

                return ResponseEntity.badRequest().body("Phone number is already registered.");

            }
        }

        int otp = otpService.generateOtp();
        otpService.sendOtpToUser(otp, String.valueOf(phonenumber));
        return ResponseEntity.ok().body("Phone number is available for registration.");


    }

    @Override
    public ResponseEntity<?> registerUser(Integer phonenumber  , String useraddress , String username ){

        ResponseEntity<?> a = validateUserPhoneNumber(phonenumber);
        if (a.getStatusCode() == HttpStatus.OK){

            User newUser = new User();
            newUser.setPhonenumber(phonenumber);
            newUser.setUsername(username);
            newUser.setUseraddress(useraddress);

            userRepository.save(newUser);
            return ResponseEntity.ok().body("User registered sucessful");

        }

        else{

            return a;
        }

    }










}
