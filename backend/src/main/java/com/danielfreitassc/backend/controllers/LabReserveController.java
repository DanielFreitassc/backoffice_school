package com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.LabReserveDTO;
import com.danielfreitassc.backend.dtos.LabReserveResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.services.LabReserveService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("lab-reserve")
public class LabReserveController {
    private final LabReserveService labReserveService;

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createReserve(@RequestBody @Valid LabReserveDTO labReserveDTO) {
        return labReserveService.create(labReserveDTO);
    }

    @GetMapping
    public List<LabReserveResponseDTO> getAllReserves() {
        return labReserveService.getAll();
    }
}
