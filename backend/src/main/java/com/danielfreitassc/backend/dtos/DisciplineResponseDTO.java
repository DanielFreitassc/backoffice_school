package com.danielfreitassc.backend.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DisciplineResponseDTO(
    UUID id,
    String name,
    String academicYear,
    int credits, 
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate createdAt
) {
    
}
