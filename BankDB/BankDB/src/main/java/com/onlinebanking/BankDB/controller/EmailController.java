package com.onlinebanking.BankDB.controller;

import com.onlinebanking.BankDB.entity.EmailGenerator;
import com.onlinebanking.BankDB.repository.EmailRepository;
import com.onlinebanking.BankDB.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailSenderService senderService;

    @Autowired
    private EmailRepository emailRepository;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest request) {
        String email = request.getEmail();

        if (email != null) { // Check if email is not null
            String otp = generateOtp();

            // Save or update OTP for the email
            saveOrUpdateOTP(email, otp);

            // Send email with OTP
            senderService.sendSimpleEmail(email,
                    "OTP verification for transaction",
                    "Hello,\n\tYour OTP is " + otp + ". Please enter the OTP and verify within the given time limit. This is an auto-generated email. For any queries, contact thebankdba@gmail.com");

            return "Email sent successfully";
        } else {
            return "Email cannot be null";
        }
    }


    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestBody EmailRequest request) {
        String email = request.getEmail();
        String otp = request.getOtp();

        // Check if the OTP matches the one stored in the database
        Optional<EmailGenerator> existingEmail = emailRepository.findById(email);
        if (existingEmail.isPresent()) {
            EmailGenerator existing = existingEmail.get();
            if (existing.getOtp().equals(otp)) {
                // OTP is valid, perform necessary actions
                return "OTP verification successful";
            } else {
                return "Invalid OTP";
            }
        } else {
            return "Email not found";
        }
    }

    private void saveOrUpdateOTP(String email, String otp) {
        if (email != null) { // Add null check
            // Check if the email exists in the database
            Optional<EmailGenerator> existingEmail = emailRepository.findById(email);
            if (existingEmail.isPresent()) {
                // If email exists, update the OTP
                EmailGenerator existing = existingEmail.get();
                existing.setOtp(otp);
                emailRepository.save(existing);
            } else {
                // If email doesn't exist, create a new entry
                EmailGenerator newEmail = new EmailGenerator(email, otp);
                emailRepository.save(newEmail);
            }
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String output = Integer.toString(randomNumber);
        while (output.length() < 6) {
            output = "0" + output;
        }
        return output;
    }
}
