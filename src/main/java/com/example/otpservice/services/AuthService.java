package com.example.otpservice.services;


import com.example.otpservice.dtos.request.AuthDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<?> generateOtp(AuthDto authDto);
/*
    ResponseEntity<?> verify(AuthDto authDto);

    ResponseEntity<?> authenticateToken(String token);*/
}
