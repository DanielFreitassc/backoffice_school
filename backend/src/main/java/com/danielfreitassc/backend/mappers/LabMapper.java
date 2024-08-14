package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.LabDTO;
import com.danielfreitassc.backend.models.LabEntity;

@Mapper(componentModel = "spring")
public interface LabMapper {
    LabDTO toDto(LabEntity labEntity);
    LabEntity toEntity(LabDTO labDTO);
}
