package com.MyPay.MyPay.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Builder
@Table(name = "shop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopid")
    private int shopid;

    @Column(name = "shopname")
    private String shopname;

    @Column(name = "shopaddress")
    private String shopaddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "merchantid")
    private Merchant merchant;

    @OneToMany(mappedBy = "shop" , cascade = CascadeType.MERGE)
    private List<Counter> counters;






}
