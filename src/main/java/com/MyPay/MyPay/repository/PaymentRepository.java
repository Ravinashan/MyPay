package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    @Query(value = "SELECT * FROM mypaydatabase.payment where paymentid = ?1", nativeQuery = true)
    Payment getPaymentByPaymentId(Integer paymentid);

    @Query(value = "SELECT * FROM mypaydatabase.payment where userid = ?1",nativeQuery = true)
    List<Payment> getPaymentsByUserId(Integer userid);



}
