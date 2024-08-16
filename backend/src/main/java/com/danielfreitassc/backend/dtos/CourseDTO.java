package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record CourseDTO(
    @NotBlank(message = "O campo nome não pode estar em branco.") String name,
    boolean available
) {
    
}
