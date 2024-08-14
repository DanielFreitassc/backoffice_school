package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.AuthenticationDTO;
import com.danielfreitassc.backend.models.UserEntity;

@Mapper(componentModel = "spring")
public interface AuhtenticationMapper {

    AuthenticationDTO toDto(UserEntity user);
    UserEntity toEntity(AuthenticationDTO authenticationDTO);  
}
