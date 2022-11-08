package com.example.otpservice.services;

import com.example.otpservice.dtos.CodeDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CodeService {
    CodeDto save(CodeDto codeDto);
    List<CodeDto> getAll();
    List<Code> findAllByUserId(Long userId);

  // List<Code> findAllByUser(User user);
}
