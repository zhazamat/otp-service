package com.example.otpservice.dtos;

import com.example.otpservice.entities.User;
import com.example.otpservice.entities.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class CodeDto {
    Long id;
    String code;
    Date endDate;
    Status status;
    UserDto userDto;
}
