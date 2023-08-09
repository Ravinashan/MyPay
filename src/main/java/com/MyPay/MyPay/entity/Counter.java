package com.MyPay.MyPay.entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Builder
@Table(name = "counter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter id")
    private int counterid;

    @Column(name = "wallet")
    private float wallet;

    @ManyToOne
    @JoinColumn(name = "shopid")
    private Shop shop;

    @OneToMany(mappedBy = "counter", cascade = CascadeType.MERGE)
    private List<Payment> payments;



}
