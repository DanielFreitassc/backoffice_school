package com.danielfreitassc.backend.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record LabReserveDTO(
    @NotNull 
    UUID userId,
    @NotNull
    UUID labId
) {
    
}
