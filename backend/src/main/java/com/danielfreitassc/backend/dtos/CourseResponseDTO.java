package com.danielfreitassc.backend.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CourseResponseDTO(
    UUID id,
    String name,
    boolean available,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate createdAt
) {
    
}
