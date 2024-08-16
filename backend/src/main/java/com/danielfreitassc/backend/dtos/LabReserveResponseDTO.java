package com.danielfreitassc.backend.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;


public record LabReserveResponseDTO(
    UUID id,
    UserResponseDTO user,
    LabResponseDTO lab,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate createdAt
) {
}
