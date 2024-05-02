package com.onlinebanking.BankDB.repository;

import com.onlinebanking.BankDB.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
