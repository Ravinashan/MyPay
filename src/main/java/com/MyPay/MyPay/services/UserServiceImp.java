package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private otpService otpService;

    private final OtpValidationService otpValidationService;

    public UserServiceImp(UserRepository userRepository , otpService otpService, OtpValidationService otpValidationService){

        this.userRepository = userRepository;
        this.otpService = otpService;
        this.otpValidationService = otpValidationService;
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
    public ResponseEntity<?> validateUserPhoneNumber(Integer phonenumber ) {

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
    public ResponseEntity<?> registerUser( String password, Integer phonenumber  , String useraddress , String username  ){

        ResponseEntity<?> a = validateUserPhoneNumber(phonenumber);
        if (a.getStatusCode() == HttpStatus.OK){

            User newUser = new User();
            newUser.setPassword(password);
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

    @Override
    public ResponseEntity<?> updateUserInformation(Integer userId, String useraddress, String username , String password, Integer phonenumber ) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUseraddress(useraddress);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhonenumber(phonenumber);

            userRepository.save(user);
            return ResponseEntity.ok().body("User information updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @Override
    public ResponseEntity<?> loginUser(Integer phonenumber , String password){

        User user = userRepository.getUserByPhoneNumber(phonenumber);

        if (user != null){
            if (user.getPassword().equals(password)){

                return new ResponseEntity<>(
                        new User(
                                user.getUserid(),
                                user.getPhonenumber(),
                                user.getUsername(),
                                user.getUseraddress()
                        ),HttpStatus.OK);
            }
            else{

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        }

        else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
