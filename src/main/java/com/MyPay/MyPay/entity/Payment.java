package com.MyPay.MyPay.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne
    @JoinColumn(name = "usercardid")
    private UserCard usercard;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "counterid")
    private Counter counter;



}
