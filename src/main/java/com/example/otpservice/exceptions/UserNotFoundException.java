package com.example.otpservice.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String massage){
        super(massage);
    }
}
