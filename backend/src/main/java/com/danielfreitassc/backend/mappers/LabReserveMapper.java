package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.danielfreitassc.backend.dtos.LabReserveDTO;
import com.danielfreitassc.backend.dtos.LabReserveResponseDTO;
import com.danielfreitassc.backend.models.LabReserve;

@Mapper(componentModel = "spring", uses = {UserMapper.class, LabMapper.class})
public interface LabReserveMapper {
    @Mapping(source = "userEntity", target = "user")
    @Mapping(source = "labEntity", target = "lab")
    LabReserveResponseDTO toResponse(LabReserve labReserve);

    LabReserveDTO toDto(LabReserve labReserve);
    LabReserve toEntity(LabReserveDTO labReserveDTO);
}



