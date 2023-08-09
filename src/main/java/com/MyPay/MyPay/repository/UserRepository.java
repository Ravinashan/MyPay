package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Override
    User getById(Integer integer);







}
