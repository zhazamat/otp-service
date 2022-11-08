package com.example.otpservice.controllers;

import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/save")
    public UserDto save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    @GetMapping("/list")
    public List<UserDto> getAll(){
        return userService.getAll();
    }

}
