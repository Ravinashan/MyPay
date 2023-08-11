package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.Payment;
import com.MyPay.MyPay.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/paymenthistory/{id}")
    public List<Payment> getPaymentsHistory(@PathVariable Integer id){
        return paymentService.getPaymentsHistory(id);

    }

    @PostMapping(value = "/counter/{counterid}/user/{userid}/usercardid/{usercardid}/pay")
    public ResponseEntity<?> pay(@RequestBody Payment payment , @PathVariable int counterid, @PathVariable int userid , @PathVariable int usercardid ){
        return paymentService.savePayment(counterid,userid,usercardid,payment);

    }




}
