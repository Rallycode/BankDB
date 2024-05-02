package com.onlinebanking.BankDB.repository;

import com.onlinebanking.BankDB.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
