package com.example.otpservice.dtos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String email;
    Date createdDate;
    Date endDate;
    Date blockedDateEnd;
    List<CodeDto> codeDos;
}
