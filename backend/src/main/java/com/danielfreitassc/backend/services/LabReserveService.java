package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.LabReserveDTO;
import com.danielfreitassc.backend.dtos.LabReserveResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.LabReserveMapper;
import com.danielfreitassc.backend.models.LabReserve;
import com.danielfreitassc.backend.repositories.LabReserveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabReserveService {
    private final LabReserveRepository labReserveRepository;
    private final LabReserveMapper labReserveMapper;

    public ResponseEntity<ResponseMessageDTO> create(LabReserveDTO labReserveDTO) {
        labReserveRepository.save(labReserveMapper.toEntity(labReserveDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Laboratório reservado com sucesso;"));
    }

    public List<LabReserveResponseDTO> getAll() {
        return labReserveRepository.findAll().stream().map(labReserveMapper::toResponse).toList();
    }

    public LabReserveResponseDTO getById(UUID id) {
        Optional<LabReserve> lab = labReserveRepository.findById(id);
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID cadastrado;");
        return labReserveMapper.toResponse(lab.get());
    }

    public ResponseEntity<ResponseMessageDTO> update(UUID id, LabReserveDTO labReserveDTO) {
        Optional<LabReserve> lab = labReserveRepository.findById(id);
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID cadastrado;");
        LabReserve labReserve = labReserveMapper.toEntity(labReserveDTO);
        labReserve.setId(id);
        labReserveRepository.save(labReserve);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório atualizado com sucesso;"));
    }

    public ResponseEntity<ResponseMessageDTO> delete(UUID id) {
        Optional<LabReserve> lab = labReserveRepository.findById(id);
        if(lab.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum lab com este ID cadastrado;");
        labReserveRepository.delete(lab.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Laboratório removido com sucesso;"));
    }
}
