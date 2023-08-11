package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Override
    User getById(Integer integer);

    @Query(value = "SELECT * FROM mypaydatabase.user where phonenumber = ?1", nativeQuery = true)
    User getUserByPhoneNumber(int  phonenumber);







}
