package com.neoquiz.api.neoquizapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoquiz.api.neoquizapi.domain.dto.AuthenticationDTO;
import com.neoquiz.api.neoquizapi.domain.dto.RegisterDTO;
import com.neoquiz.api.neoquizapi.repository.UseRepository;
@RequestMapping("auth")
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired 
    private UseRepository useRepository; 
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthenticationDTO  data){
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody  RegisterDTO data){
        if(this.useRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.roles());

       this.useRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
