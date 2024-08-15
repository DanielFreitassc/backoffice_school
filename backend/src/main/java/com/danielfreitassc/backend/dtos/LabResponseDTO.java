package com.danielfreitassc.backend.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public record LabResponseDTO(
    UUID id,
    String name,
    String local,
    int capacity,
    boolean available,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate createdAt
) {
    
}
