package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Counter;
import com.MyPay.MyPay.entity.Payment;
import com.MyPay.MyPay.entity.User;
import com.MyPay.MyPay.entity.UserCard;
import com.MyPay.MyPay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CounterService counterService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCardService userCardService;


    public PaymentServiceImp(PaymentRepository paymentRepository){

        this.paymentRepository = paymentRepository;

    }

    @Override
    public List<Payment> getPaymentsHistory(Integer userid){

        List<Payment> payments = paymentRepository.getPaymentsByUserId(userid);
        return payments;


    }

    @Override
    public ResponseEntity<?> savePayment(int counterid, int userid, int usercardid ,  Payment payment) {

        Payment newPayment = new Payment();
        newPayment.setAmount(payment.getAmount());
        Date date = new Date();
        newPayment.setTimestamp(new Timestamp(date.getTime()));

        Optional<Counter> c = Optional.ofNullable(counterService.findCounterById(counterid));
        if(c.isPresent()){
            newPayment.setCounter(c.get());
        }

        Optional<User> u = userService.findUserById(userid);
        if(u.isPresent()){
            newPayment.setUser(u.get());
        }

        Optional<UserCard> uc = userCardService.findUserCardById(userid);
        if(uc.isPresent()){
            newPayment.setUsercard(uc.get());
        }


        Payment p = paymentRepository.save(newPayment);

        if (p!= null){

            counterService.updateWalletWithAmount(counterid,payment.getAmount());

        }


        return ResponseEntity.ok().body("payment is recorded successfully");
    }


}
