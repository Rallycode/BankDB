package com.onlinebanking.BankDB.mapper;


import com.onlinebanking.BankDB.dto.AccountDto;
import com.onlinebanking.BankDB.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){

        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance(),
                accountDto.getAadharNo(),
                accountDto.getAccountType(),
                accountDto.getEmail(),
                accountDto.getDob(),
                accountDto.getPhoneNo(),
                accountDto.getPincode(),
                accountDto.getState(),
                accountDto.getCity(),
                accountDto.getLocality()
        );

        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getPhoneNo(),
                account.getDob(),
                account.getAccountType(),
                account.getEmail(),
                account.getAadharNo(),
                account.getPincode(),
                account.getState(),
                account.getCity(),
                account.getLocality(),
                account.getBalance()
        );

        return accountDto;
    }
}
