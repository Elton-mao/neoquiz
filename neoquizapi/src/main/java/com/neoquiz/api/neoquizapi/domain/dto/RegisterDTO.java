package com.neoquiz.api.neoquizapi.domain.dto;

import com.neoquiz.api.neoquizapi.domain.model.UserRoles;

public record RegisterDTO(String login, String password, UserRoles roles) {
} 
