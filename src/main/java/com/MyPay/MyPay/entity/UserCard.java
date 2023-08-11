
package com.MyPay.MyPay.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Table(name = "usercard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardid;

    @Column(name = "cardtype")
    private String cardtype;

    @Column(name = "cardnumber")
    private String cardnumber;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "expirydate")
    private LocalDate expirydate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;








}
