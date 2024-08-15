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

import com.danielfreitassc.backend.dtos.LabDTO;
import com.danielfreitassc.backend.dtos.LabListDTO;
import com.danielfreitassc.backend.dtos.LabResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.services.LabService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("lab")
@RequiredArgsConstructor
public class LabController {
    private final LabService labService;

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createLab(@RequestBody @Valid LabDTO labDTO) {
        return labService.create(labDTO);
    }

    @GetMapping
    public List<LabResponseDTO> getAllLabs() {
        return labService.getAll();
    }

    @GetMapping("{id}")
    public LabResponseDTO getById(@PathVariable UUID id) {
        return labService.getById(id);
    }

    @GetMapping("name/{name}")
    public LabResponseDTO getByName(@PathVariable String name) {
        return labService.getByName(name);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> updateLab(@PathVariable UUID id, @RequestBody @Valid LabDTO labDTO) {
        return labService.update(id, labDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> deleteLab(@PathVariable UUID id) {
        return labService.delete(id);
    }

    @GetMapping("list")
    public List<LabListDTO> getSelects() {
        return labService.getAllSelects();
    }
} 
