package com.onlinebanking.BankDB.repository;

import com.onlinebanking.BankDB.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    // Method to find all transactions related to a specific account (sent or received)
    List<Transaction> findByFromAccountOrToAccount(Long fromAccount, Long toAccount);

    // Method to find all transactions where the account was used for sending funds
    List<Transaction> findByFromAccount(Long fromAccount);

    // Method to find all transactions where the account was used for receiving funds
    List<Transaction> findByToAccount(Long toAccount);
}
