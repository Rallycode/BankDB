package com.onlinebanking.BankDB.service;

import com.onlinebanking.BankDB.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);//admin
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amount);//adding some money to the account
    AccountDto withdraw(Long id, double amount);//getting some money from account
    List<AccountDto> getAllAccounts();//admin
    void deleteAccount(Long id);//admin
}