package com.neoquiz.api.neoquizapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoquiz.api.neoquizapi.domain.dto.AuthenticationDTO;
import com.neoquiz.api.neoquizapi.domain.dto.RegisterDTO;
import com.neoquiz.api.neoquizapi.domain.model.user.User;
import com.neoquiz.api.neoquizapi.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired 
    private UserRepository useRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(UsernamePassword);
        

        return ResponseEntity.ok().build();
        
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.useRepository.findByLogin(data.login()) !=null) return ResponseEntity.badRequest().build();
        
        String encriptedPassword = new BCryptPasswordEncoder().encode(data.password());
        
        User newUser = new User(data.login(),encriptedPassword,data.role());
        
        this.useRepository.save(newUser);
        
        return ResponseEntity.ok().build();

    }

}
