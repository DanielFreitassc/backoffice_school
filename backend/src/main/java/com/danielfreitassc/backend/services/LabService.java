package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.LabAvailable;
import com.danielfreitassc.backend.dtos.LabDTO;
import com.danielfreitassc.backend.dtos.LabListDTO;
import com.danielfreitassc.backend.dtos.LabResponseDTO;
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

    public List<LabResponseDTO> getAll() {
        return labRepository.findAll().stream().map(labMapper::toResponseDTO).toList();
    }

    public LabResponseDTO getById(UUID id) {
        Optional<LabEntity> lab = labRepository.findById(id); 
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID encontrado.");
        return labMapper.toResponseDTO(lab.get());
    } 

    public LabResponseDTO getByName(String name) {
        Optional<LabEntity> lab = labRepository.findByName(name); 
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID encontrado.");
        return labMapper.toResponseDTO(lab.get());
    } 

    public ResponseEntity<ResponseMessageDTO> update(UUID id, LabDTO labDTO) {
        Optional<LabEntity> lab = labRepository.findById(id); 
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID encontrado.");
        LabEntity labEntity = labMapper.toEntity(labDTO);
        labEntity.setId(id);
        labRepository.save(labEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório cadastrado com sucesso"));
    }

    public ResponseEntity<ResponseMessageDTO> delete(UUID id) {
        Optional<LabEntity> lab = labRepository.findById(id); 
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum laboratório com este ID encontrado.");
        labRepository.delete(lab.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório removido com sucesso"));
    }

    public List<LabListDTO> getAllSelects() {
       return labRepository.findAll().stream().map(labMapper::toList).toList();
    }

    public ResponseEntity<ResponseMessageDTO> toAvailable(UUID id, LabAvailable labDTO) {
        Optional<LabEntity> lab = labRepository.findById(id); 
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum laboratório com este ID encontrado.");
        
        LabEntity labEntity = lab.get();

        if (labEntity.isAvailable() != labDTO.available()) {
            labEntity.setAvailable(labDTO.available());
            labRepository.save(labEntity);
        }
        
        labRepository.save(labEntity);

        if(labEntity.isAvailable()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório agora está disponível."));
        } else if(!labEntity.isAvailable()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório agora está indisponível."));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Erro ao atualizar estado do laboratório"));
        }
    }
}
