package com.onlinebanking.BankDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private Long phoneNo;
    private String dob;
    private String accountType;
    private String email;
    private String aadharNo;
    private Long pincode;
    private String state;
    private String city;
    private String locality;
    private double balance;

}
