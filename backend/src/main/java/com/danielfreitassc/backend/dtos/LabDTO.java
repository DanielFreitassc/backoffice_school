package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LabDTO(
    @NotBlank(message = "Nome não pode estar em branco.") String name,
    @NotBlank(message = "Local não pode estar em branco.") String local,
    @NotNull(message = "Capacidade não pode ser nula") @Min(value = 0,message = "O menor valor para capacidade é zero.") int capacity,
    boolean available
) {
    
}
