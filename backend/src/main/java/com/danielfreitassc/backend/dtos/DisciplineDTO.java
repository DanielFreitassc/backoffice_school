package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplineDTO(
    @NotBlank(message = "O campo nome não pode estar em branco.") String name,
    @NotBlank(message = "O campo nome não pode estar em branco.") String academicYear,
    @NotNull(message = "O campo creditos não pode ser nulo") @Min(value = 0,message = "O menor valor para créditos é zero.") int credits 
) {
    
}
