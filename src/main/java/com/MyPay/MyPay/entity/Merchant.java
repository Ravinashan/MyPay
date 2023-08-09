package com.MyPay.MyPay.entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Builder
@Table(name = "merchant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int merchantid;

    @Column(name = "phonenumber")
    private int phonenumber;

    @Column(name = "merchantname")
    private String merchantname;

    @Column(name = "merchantaddress")
    private String merchantaddress;

    @OneToMany(mappedBy = "merchant" , cascade = CascadeType.MERGE)
    private List<Card> cards;

    @OneToMany(mappedBy = "merchant" , cascade = CascadeType.MERGE)
    private List<Shop> shops;


}
