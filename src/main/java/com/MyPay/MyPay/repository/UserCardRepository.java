package com.MyPay.MyPay.repository;

import com.MyPay.MyPay.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCardRepository extends JpaRepository<UserCard,Integer> {

    @Query(value = "SELECT * FROM mypaydatabase.usercard where userid = ?1",nativeQuery = true)
    List<UserCard> getUserCardsByUserId(Integer userid);



}
