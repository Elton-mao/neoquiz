package com.neoquiz.api.neoquizapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoquiz.api.neoquizapi.domain.model.user.User;
import com.neoquiz.api.neoquizapi.repository.UserRepository;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserRepository uRepository ;

    @PostMapping
    public User userSave(@RequestBody User user){
        uRepository.save(user);
        return user;
    }
    @GetMapping
    public List<User> findAll(){
        return uRepository.findAll();
    }
}
