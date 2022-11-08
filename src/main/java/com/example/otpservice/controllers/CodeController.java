package com.example.otpservice.controllers;

import com.example.otpservice.dtos.CodeDto;
import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import com.example.otpservice.services.CodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/code")
public class CodeController {
    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/save")
    public CodeDto save(@RequestBody CodeDto codeDto){
        return codeService.save(codeDto);
}
   @GetMapping("/{id}")
    public List<Code> findAllByUserId(@PathVariable Long user_id){
        return codeService.findAllByUserId(user_id);
   }

   @GetMapping("/list")
    public List<CodeDto>getAll(){
        return codeService.getAll();
   }
}
