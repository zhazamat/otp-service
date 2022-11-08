package com.example.otpservice.services.impl;

import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import com.example.otpservice.mappers.CodeMapper;
import com.example.otpservice.mappers.UserMapper;
import com.example.otpservice.repositories.CodeRepository;
import com.example.otpservice.repositories.UserRepository;
import com.example.otpservice.services.CodeService;
import com.example.otpservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
private final UserRepository userRepository;

private final CodeRepository codeRepository;
private  final CodeService codeService;

    public UserServiceImpl(UserRepository userRepository, CodeRepository codeRepository, CodeService codeService) {
        this.userRepository = userRepository;

        this.codeRepository = codeRepository;
        this.codeService = codeService;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user=UserMapper.INSTANCE.user(userDto);
        user=userRepository.save(user);
        userDto=UserMapper.INSTANCE.userToUserDto(user);
        return userDto;
    }
    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            List<Code>codeList=codeService.findAllByUserId(user.getId());


            userDtoList.add(UserMapper.INSTANCE.userDto(user, codeList));

        }
        return userDtoList;
    }

    @Override
    public User findByEmail(String email) {

        return  userRepository.findByEmail(email);
    }

    @Override
    public List<Code> findAllByUser(User user) {
        return null;
    }
}
