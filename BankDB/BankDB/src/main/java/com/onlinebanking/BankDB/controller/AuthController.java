package com.onlinebanking.BankDB.controller;

import com.onlinebanking.BankDB.entity.Admin;
import com.onlinebanking.BankDB.entity.User;
import com.onlinebanking.BankDB.repository.AdminRepository;
import com.onlinebanking.BankDB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Check if the user is an admin
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            // Admin authentication successful
            return ResponseEntity.ok().body(new RedirectView("/api/accounts"));
        }

        // Check if the user is a regular user
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // User authentication successful
            return ResponseEntity.ok().body(new RedirectView("/api/accounts"));
        }

        // Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<Long> returnAccountID(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
