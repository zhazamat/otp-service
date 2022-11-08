package com.example.otpservice.services.impl;


import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.dtos.request.AuthDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import com.example.otpservice.entities.enums.Status;
import com.example.otpservice.exceptions.UserBlockedException;
import com.example.otpservice.exceptions.UserNotFoundException;
import com.example.otpservice.repositories.CodeRepository;
import com.example.otpservice.repositories.UserRepository;
import com.example.otpservice.services.AuthService;
import com.example.otpservice.services.MailService;
import com.example.otpservice.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final CodeRepository codeRepository;
    private final long OTP_TTL = 60_000;

    @Value("${jwt.token.expired}")
    private String token_TTL;
    @Value("${jwt.token.secret}")
    private String secret;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, CodeRepository codeRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.codeRepository = codeRepository;
        this.mailService = mailService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void initSecret() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public static long getOtpCode() {
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

    public ResponseEntity<?> generateOtp(AuthDto authDto) {
        User user = userRepository.findByEmail(authDto.getEmail());

        String message = "Created account.Your code is sending email";
        if (Objects.isNull(user)) {
            UserDto userDto = new UserDto();
            userDto.setEmail(authDto.getEmail());
            userDto.setCreatedDate(new Date());
            userService.save(userDto);
            Code code=new Code();
            code.setCode(String.valueOf(getOtpCode()));
            code.setUserId(userService.findByEmail(authDto.getEmail()));
            code.setEndDate(new Date(System.currentTimeMillis() + OTP_TTL));
            code.setStatus(Status.CREATED);
            codeRepository.save(code);
            mailService.sendEmail(userService.findByEmail(authDto.getEmail()).getEmail(), "Successful created account .Your  code is:" + getOtpCode(), "otp code");

        }

          else if (user.getBlockedDateEnd() != null) {
                throw new UserBlockedException("Account is blocked. Try after a certain time");
            }  else if(user.getBlockedDateEnd()== null){
               Code code1=new Code();
                code1.setCode(String.valueOf(getOtpCode()));
                code1.setUserId(userService.findByEmail(authDto.getEmail()));
                code1.setEndDate(new Date(System.currentTimeMillis() + OTP_TTL));
                code1.setStatus(Status.CANCELED);
                codeRepository.save(code1);
               message="Your NEW code";
                mailService.sendEmail(userService.findByEmail(authDto.getEmail()).getEmail(), "your NEW code is:" + getOtpCode(), "otp code");
            }
          return ResponseEntity.ok(message);
        }
    }


/*
    @Override
    public ResponseEntity<?> verify(AuthDto authDto) {
        User user = userRepository.findByEmail(authDto.getEmail());
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found");
        }
        if (!bCryptPasswordEncoder.matches(authDto.getCode(), user.getCode())) {
            return new ResponseEntity<>("Code note matches",HttpStatus.CONFLICT);
        }

        if (user.getOtpCodeEndDate().before(new Date())) {
            return  new ResponseEntity<>("Otp code is expired", HttpStatus.CONFLICT);
        }
        Claims claims= Jwts.claims().setSubject(user.getEmail());
        //claims.put("roles",)
        Date now=new Date();
        Date expirationDate=new Date(now.getTime()+Long.valueOf(token_TTL));
        String token=Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
        return ResponseEntity.ok(token);
    }

    /**
     * @param token
     * @return

    @Override
    public ResponseEntity<?> authenticateToken(String token) {
     String userName=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        return ResponseEntity.ok(userName);
    }
    */

