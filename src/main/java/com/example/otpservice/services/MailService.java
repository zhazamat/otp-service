package com.example.otpservice.services;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendEmail(String recipient,String text,String subject);
}
