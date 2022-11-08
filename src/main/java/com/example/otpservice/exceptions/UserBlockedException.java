package com.example.otpservice.exceptions;

public class UserBlockedException extends RuntimeException{
  public UserBlockedException(String message){
      super((message));
  }
}
