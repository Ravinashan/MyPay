package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Payment;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> getPaymentsHistory(Integer userid);


    ResponseEntity<?> savePayment(int counterid, int userid, int usercardid , Payment payment);
}
