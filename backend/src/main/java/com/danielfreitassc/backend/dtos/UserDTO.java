package com.danielfreitassc.backend.dtos;


import com.danielfreitassc.backend.models.UserRole;

public record UserDTO(
    String name,
    String username,
    String password,
    UserRole role
) {
    
}
