package com.example.otpservice.mappers;

import com.example.otpservice.dtos.CodeDto;
import com.example.otpservice.dtos.UserDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CodeMapper {
   CodeMapper INSTANCE= Mappers.getMapper(CodeMapper.class);
    Code codeDtoToCode(CodeDto codeDto);
    CodeDto codeToCodeDto(Code code);
    default  Code code(CodeDto codeDto){
        Code code=new Code();
        code.setCode(codeDto.getCode());
        code.setEndDate(codeDto.getEndDate());
        code.setStatus(codeDto.getStatus());
        code.setId(codeDto.getId());
        code.setUserId(UserMapper.INSTANCE.user(codeDto.getUserDto()));
        return code;
    }
    default CodeDto codeDto(Code code){
        CodeDto codeDto=new CodeDto();
        codeDto.setCode(code.getCode());
        codeDto.setStatus(code.getStatus());
        codeDto.setId(code.getId());
        codeDto.setUserDto(UserMapper.INSTANCE.userToUserDto(code.getUserId()));
        codeDto.setEndDate(code.getEndDate());
        return codeDto;
    }
}
