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

import com.danielfreitassc.backend.dtos.DisciplineDTO;
import com.danielfreitassc.backend.dtos.DisciplineResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.services.DisciplineService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("discipline")
public class DisciplineController {
    private final DisciplineService disciplineService;

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createDiscipline(@RequestBody @Valid DisciplineDTO disciplineDTO) {
        return disciplineService.create(disciplineDTO);
    }

    @GetMapping
    public List<DisciplineResponseDTO> getAllDisciplines() {
        return disciplineService.getAll();
    }

    @GetMapping("{id}")
    public DisciplineResponseDTO getById(@PathVariable UUID id) {
        return disciplineService.getById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> updateDiscipline(@PathVariable UUID id, @RequestBody @Valid DisciplineDTO disciplineDTO) {
        return disciplineService.update(id, disciplineDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> deleteDiscipline(@PathVariable UUID id) {
        return disciplineService.delete(id);
    }
}
