package com.MyPay.MyPay.services;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class otpService {

    private Random random = new Random();

    public int generateOtp() {
        // Generate a 6-digit OTP
        return 100000 + random.nextInt(900000);
    }

    public void sendOtpToUser(int otp, String phoneNumber) {

        System.out.println("Sending OTP " + otp + " to phone number: " + phoneNumber);
    }
}

