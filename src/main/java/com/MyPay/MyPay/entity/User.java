package com.MyPay.MyPay.entity;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userid;


    @Column(name = "phonenumber")
    private int phonenumber;

    @Column(name = "username")
    private String username;

    @Column(name = "useraddress")
    private String useraddress;

    @Column(name = "passsword")
    private String password;



    public User(String password , int phonenumber , String useraddress ,  String username ){

        this.phonenumber = phonenumber;
        this.useraddress = useraddress;
        this.username = username;
        this.password = password;


    }
    public User(int userid , int phonenumber , String useraddress ,  String username ){

        this.phonenumber = phonenumber;
        this.useraddress = useraddress;
        this.username = username;
        this.userid = userid;


    }


    public User(int userid , String password, int phonenumber , String useraddress ,  String username   ){

        this.userid = userid;
        this.phonenumber = phonenumber;
        this.useraddress = useraddress;
        this.username = username;
        this.password = password;


    }

    @OneToMany(mappedBy = "user" , cascade = CascadeType.MERGE)
    private List<UserCard> cards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Payment> payments;






}
