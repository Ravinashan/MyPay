package com.MyPay.MyPay.services;

import org.springframework.stereotype.Service;

@Service
public class OtpValidationService {

    private final otpService otpService;

    public OtpValidationService(otpService otpService) {
        this.otpService = otpService;
    }

    public boolean validateOtp(Integer otp, Integer phoneNumber) {
        // Call your otpService to validate the OTP
        int generatedOtp = otpService.generateOtp();

        return otp.equals(generatedOtp);
    }
}
