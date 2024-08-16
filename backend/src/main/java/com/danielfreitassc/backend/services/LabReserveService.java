package com.danielfreitassc.backend.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.danielfreitassc.backend.dtos.LabReserveDTO;
import com.danielfreitassc.backend.dtos.LabReserveResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.LabReserveMapper;
import com.danielfreitassc.backend.repositories.LabReserveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabReserveService {
    private final LabReserveRepository labReserveRepository;
    private final LabReserveMapper labReserveMapper;

    public ResponseEntity<ResponseMessageDTO> create(LabReserveDTO labReserveDTO) {
        labReserveRepository.save(labReserveMapper.toEntity(labReserveDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Laboratório reservado com sucesso."));
    }

    public List<LabReserveResponseDTO> getAll() {
        return labReserveRepository.findAll().stream().map(labReserveMapper::toResponse).toList();
    }
}
