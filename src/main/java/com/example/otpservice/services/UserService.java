package com.example.otpservice.services;

import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

UserDto save(UserDto userDto) ;
List<UserDto> getAll();

    User findByEmail(String email);
    List<Code> findAllByUser(User user);
}
