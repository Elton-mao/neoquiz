package com.neoquiz.api.neoquizapi.domain.dto;

import com.neoquiz.api.neoquizapi.domain.model.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
} 
