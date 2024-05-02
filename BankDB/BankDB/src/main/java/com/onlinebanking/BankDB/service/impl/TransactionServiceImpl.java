package com.onlinebanking.BankDB.service.impl;

import com.onlinebanking.BankDB.entity.Account;
import com.onlinebanking.BankDB.entity.Transaction;
import com.onlinebanking.BankDB.repository.AccountRepository;
import com.onlinebanking.BankDB.repository.TransactionRepository;
import com.onlinebanking.BankDB.util.OtpUtil;
import com.onlinebanking.BankDB.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final EmailUtil emailUtil;
    private final OtpUtil otpUtil;

    @Autowired
    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, OtpUtil otpUtil, EmailUtil emailUtil1) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.emailUtil = emailUtil1;
        this.otpUtil = otpUtil;
    }


    @Transactional
    public void performTransaction(Long fromAccountId, Long toAccountId, Long amount, String description) {
        // Retrieve accounts
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        // Check if fromAccount has sufficient balance
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance in source account");
        }

        // Perform the transaction
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Update accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create a transaction entity and save it
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccountId);
        transaction.setToAccount(toAccountId);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setStatus("Completed");
        transaction.setTransactionTimeStamp(new Date());

        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactionHistoryForAccount(Long accountId) {
        // Retrieve all transactions related to the specified account (sent or received)
        return transactionRepository.findByFromAccountOrToAccount(accountId, accountId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getSentTransactionsForAccount(Long accountId) {
        // Retrieve all transactions where the specified account was used for sending funds
        return transactionRepository.findByFromAccount(accountId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getReceivedTransactionsForAccount(Long accountId) {
        // Retrieve all transactions where the specified account was used for receiving funds
        return transactionRepository.findByToAccount(accountId);
    }
}