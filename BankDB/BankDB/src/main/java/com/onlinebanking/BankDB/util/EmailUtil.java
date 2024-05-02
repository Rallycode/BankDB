package com.onlinebanking.BankDB.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("OTP verification for transaction");
        simpleMailMessage.setSubject("Hello,\n\tYour OTP is "+otp+". Please enter the otp and verify within the given time limit. This an auto-generated for any queries contact thebankdba@gmail.com");

        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("OTP verification for transaction");
        mimeMessageHelper.setText("\"Hello,\\n\\tYour OTP is \"+otp+\". Please enter the otp and verify within the given time limit. This an auto-generated for any queries contact thebankdba@gmail.com\"");
        javaMailSender.send(mimeMessage);
    }
}
