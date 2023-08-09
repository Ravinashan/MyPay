package com.MyPay.MyPay.entity;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Builder
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private int paymentid;

    @Column(name = "amount")
    private float amount;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "counterid")
    private Counter counter;



}
