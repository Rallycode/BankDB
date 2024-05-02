package com.onlinebanking.BankDB.controller;

public class EmailRequest {
    private String email; // Add email field
    private String otp;
    public String getEmail() {
        return email;
    }

    public String getOtp(){
        return otp;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
}
