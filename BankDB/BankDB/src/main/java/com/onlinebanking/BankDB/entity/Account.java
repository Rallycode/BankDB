package com.onlinebanking.BankDB.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    private double balance;
    private String aadharNo;
    private String accountType;
    @Column(name = "email",unique = true)
    private String email;
    private String dob;
    private Long phoneNo;
    private Long pincode;
    private String city;
    private String locality;
    private String state;
    @Column(name = "account_created_at")
    @CreationTimestamp
    private Date accountCreatedAt;

    @OneToOne(mappedBy = "account")
    private User user;
    // Constructor excluding accountCreatedAt
    @Builder // Adding this annotation for builder pattern support
    public Account(Long id, String accountHolderName, double balance, String aadharNo, String accountType,String email, String dob, Long phoneNo, Long pincode, String city,String state, String locality) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.aadharNo = aadharNo;
        this.accountType = accountType;
        this.email = email;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.locality = locality;
    }
}
