package com.neoquiz.api.neoquizapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neoquiz.api.neoquizapi.repository.UseRepository;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    UseRepository uRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return uRepository.findByLogin(username);
    }
    
}
