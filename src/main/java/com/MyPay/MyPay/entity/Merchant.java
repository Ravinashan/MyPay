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

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "merchant" , cascade = CascadeType.MERGE)
    private List<MerchantCard> cards;

    @OneToMany(mappedBy = "merchant" , cascade = CascadeType.MERGE)
    private List<Shop> shops;

    public Merchant(String password , int phonenumber , String useraddress ,  String username ){

        this.phonenumber = phonenumber;
        this.merchantaddress = useraddress;
        this.merchantname = username;
        this.password = password;


    }
    public Merchant(int userid , int phonenumber , String useraddress ,  String username ){

        this.phonenumber = phonenumber;
        this.merchantaddress = useraddress;
        this.merchantname = username;
        this.merchantid = userid;


    }


    public Merchant(int userid , String password, int phonenumber , String useraddress ,  String username   ){

        this.merchantid = userid;
        this.phonenumber = phonenumber;
        this.merchantaddress = useraddress;
        this.merchantname = username;
        this.password = password;


    }




}
