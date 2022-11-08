package com.example.otpservice.services.impl;

import com.example.otpservice.dtos.CodeDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import com.example.otpservice.mappers.CodeMapper;
import com.example.otpservice.repositories.CodeRepository;
import com.example.otpservice.repositories.UserRepository;
import com.example.otpservice.services.CodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl implements CodeService {
    private  final CodeRepository codeRepository;

    private final UserRepository userRepository;

    public CodeServiceImpl(CodeRepository codeRepository, UserRepository userRepository) {
        this.codeRepository = codeRepository;

        this.userRepository = userRepository;
    }

    @Override
    public CodeDto save(CodeDto codeDto) {
        Code code=CodeMapper.INSTANCE.code(codeDto);
        code=codeRepository.save(code);
        codeDto=CodeMapper.INSTANCE.codeToCodeDto(code);
        return codeDto;
    }
    @Override
    public List<CodeDto> getAll(){

        return codeRepository.findAll()
                .stream()
                .map(CodeMapper.INSTANCE::codeDto)
                .collect(Collectors.toList());
    }


   /* @Override
    public List<Code> findAllByUser(User user) {
        return codeRepository.findAllByUser(user);
    }*/

    @Override
    public List<Code> findAllByUserId(Long user_id) {
        return codeRepository.findAllByUserId(user_id);
    }

}
