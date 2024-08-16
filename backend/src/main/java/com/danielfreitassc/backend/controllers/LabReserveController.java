package com.danielfreitassc.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("{id}")
    public LabReserveResponseDTO getReserveById(@PathVariable UUID id) {
        return labReserveService.getById(id);
    }
    @PutMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> updateReserve(@PathVariable UUID id, @RequestBody @Valid LabReserveDTO labReserveDTO) {
        return labReserveService.update(id, labReserveDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> delete(@PathVariable UUID id) {
        return labReserveService.delete(id);
    }
}
