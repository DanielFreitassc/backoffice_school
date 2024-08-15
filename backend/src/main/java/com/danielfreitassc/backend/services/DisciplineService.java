package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.DisciplineDTO;
import com.danielfreitassc.backend.dtos.DisciplineResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.DisciplineMapper;
import com.danielfreitassc.backend.models.DisciplineEntity;
import com.danielfreitassc.backend.repositories.DisciplineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;


    public ResponseEntity<ResponseMessageDTO> create(DisciplineDTO disciplineDTO) {
        disciplineRepository.save(disciplineMapper.toEntity(disciplineDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Disciplina criada com sucesso."));
    }

    public List<DisciplineResponseDTO> getAll() {
        return disciplineRepository.findAll().stream().map(disciplineMapper::toResponse).toList();
    } 

    public DisciplineResponseDTO getById(UUID id) {
        Optional<DisciplineEntity> discipline = disciplineRepository.findById(id);
        if(discipline.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma disciplina com este ID cadastrada no momento.");
        return disciplineMapper.toResponse(discipline.get());
    }

    public ResponseEntity<ResponseMessageDTO> update(UUID id, DisciplineDTO disciplineDTO) {
        Optional<DisciplineEntity> discipline = disciplineRepository.findById(id);
        if(discipline.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma disciplina com este ID cadastrada no momento.");
        DisciplineEntity disciplineEntity = disciplineMapper.toEntity(disciplineDTO);
        disciplineEntity.setId(id);
        disciplineRepository.save(disciplineEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Disciplina atualizada com sucesso"));
    }

    public ResponseEntity<ResponseMessageDTO> delete(UUID id) {
        Optional<DisciplineEntity> discipline = disciplineRepository.findById(id);
        if(discipline.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma disciplina com este ID cadastrada no momento.");
        disciplineRepository.delete(discipline.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Disciplina  removida com sucesso"));
    }

}
