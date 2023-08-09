package com.MyPay.MyPay.entity;
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
    @Column(name = "shop id")
    private int shopid;

    @Column(name = "shop name")
    private String shopname;

    @Column(name = "shop address")
    private String shopaddress;

    @ManyToOne
    @JoinColumn(name = "merchantid")
    private Merchant merchant;

    @OneToMany(mappedBy = "shop" , cascade = CascadeType.MERGE)
    private List<Counter> counters;






}
