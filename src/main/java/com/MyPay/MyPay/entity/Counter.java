package com.MyPay.MyPay.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "counterid")
    private int counterid;

    @Column(name = "wallet")
    private float wallet;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shopid")
    private Shop shop;

    @OneToMany(mappedBy = "counter", cascade = CascadeType.MERGE)
    private List<Payment> payments;



}
