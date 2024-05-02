package com.onlinebanking.BankDB.repository;

import com.onlinebanking.BankDB.entity.EmailGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailGenerator,String> {
}
