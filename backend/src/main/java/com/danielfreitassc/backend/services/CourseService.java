package com.danielfreitassc.backend.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.CourseAvailableDTO;
import com.danielfreitassc.backend.dtos.CourseDTO;
import com.danielfreitassc.backend.dtos.CourseResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.mappers.CourseMapper;
import com.danielfreitassc.backend.models.CourseEntity;
import com.danielfreitassc.backend.repositories.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;


    public ResponseEntity<ResponseMessageDTO> create(CourseDTO courseDTO) {
        courseRepository.save(courseMapper.toEntity(courseDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Curso criado com sucesso."));
    }

    public List<CourseResponseDTO> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
    }

    public CourseResponseDTO getById(UUID id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if(course.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso com este ID.");
        return courseMapper.toResponse(course.get());
    }

    public ResponseEntity<ResponseMessageDTO> update(UUID id, CourseDTO courseDTO) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if(course.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso com este ID.");
        CourseEntity courseEntity = courseMapper.toEntity(courseDTO);
        courseEntity.setId(id);
        courseRepository.save(courseEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Curso atualizado com sucesso."));
    }

    public ResponseEntity<ResponseMessageDTO> delete(UUID id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if(course.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso com este ID.");
        courseRepository.delete(course.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Curso removido com sucesso."));
    }

    public ResponseEntity<ResponseMessageDTO> setAvailable(UUID id, CourseAvailableDTO courseDTO) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if(course.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum curso com este ID");
        CourseEntity courseEntity = course.get();

        if(course.get().isAvailable() != courseDTO.available()) {
          courseEntity.setAvailable(courseDTO.available()); 
          courseRepository.save(courseEntity); 
        }
        
        if(courseEntity.isAvailable() == true) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Curso agora está disponível."));
        }  else if(courseEntity.isAvailable() == false) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Curso agora está indisponível."));
        }  else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Erro."));
        }

    }
}
