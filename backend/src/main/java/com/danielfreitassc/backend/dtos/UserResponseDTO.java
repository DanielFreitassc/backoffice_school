package com.danielfreitassc.backend.dtos;

import java.util.UUID;

import com.danielfreitassc.backend.models.UserRole;

public record UserResponseDTO(
    UUID id,
    String name,
    String username,
    UserRole role
) {
    
}
