package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.LabAvailable;
import com.danielfreitassc.backend.dtos.LabDTO;
import com.danielfreitassc.backend.dtos.LabListDTO;
import com.danielfreitassc.backend.dtos.LabResponseDTO;
import com.danielfreitassc.backend.models.LabEntity;

@Mapper(componentModel = "spring")
public interface LabMapper {
    LabDTO toDto(LabEntity labEntity);
    LabResponseDTO toResponseDTO(LabEntity labEntity);
    LabListDTO toList(LabEntity labEntity);
    LabAvailable toAvailable(LabEntity labEntity);
    LabEntity toEntity(LabDTO labDTO);

}
