package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.DisciplineDTO;
import com.danielfreitassc.backend.dtos.DisciplineResponseDTO;
import com.danielfreitassc.backend.models.DisciplineEntity;

@Mapper(componentModel = "spring")
public interface DisciplineMapper {
    DisciplineDTO toDto(DisciplineEntity disciplineEntity);
    DisciplineResponseDTO toResponse(DisciplineEntity disciplineEntity);
    DisciplineEntity toEntity(DisciplineDTO disciplineDTO);
}
