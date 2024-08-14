package com.danielfreitassc.backend.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.danielfreitassc.backend.dtos.LabDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.LabMapper;
import com.danielfreitassc.backend.models.LabEntity;
import com.danielfreitassc.backend.repositories.LabRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabService {
    private final LabRepository labRepository;
    private final LabMapper labMapper;

    public ResponseEntity<ResponseMessageDTO> create(LabDTO labDTO) {
        LabEntity labEntity = labMapper.toEntity(labDTO);
        labRepository.save(labEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Laboratorio criado com sucesso"));
    }

}
