package com.neoquiz.api.neoquizapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.neoquiz.api.neoquizapi.domain.model.user.User;

public interface UseRepository extends JpaRepository<User, Long>{
    UserDetails findByLogin(String login);
    

}
