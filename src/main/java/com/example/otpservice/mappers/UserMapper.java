package com.example.otpservice.mappers;

import com.example.otpservice.dtos.CodeDto;
import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    default User user(UserDto userDto){
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setEndDate(userDto.getEndDate());
        user.setCreatedDate(userDto.getCreatedDate());
        user.setBlockedDateEnd(userDto.getBlockedDateEnd());
        user.setId(userDto.getId());
        return user;
    }
    default UserDto userDto(User user, List<Code> code){
        List<CodeDto>codeDtos=code.stream().map(CodeMapper.INSTANCE::codeDto).collect(Collectors.toList());
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setBlockedDateEnd(user.getBlockedDateEnd());
        userDto.setCodeDos(codeDtos);
        userDto.setId(user.getId());
        return userDto;
    }
}
