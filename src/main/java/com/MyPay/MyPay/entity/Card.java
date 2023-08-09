package com.MyPay.MyPay.entity;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardid;

    @Column(name = "card type")
    private String cardtype;

    @Column(name = "card number")
    private int cardnumber;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "expiry date")
    private LocalDate expirydate;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "merchantid")
    private Merchant merchant;




}
