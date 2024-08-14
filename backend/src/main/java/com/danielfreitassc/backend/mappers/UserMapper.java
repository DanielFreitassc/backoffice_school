package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.UserDTO;
import com.danielfreitassc.backend.dtos.UserResponseDTO;
import com.danielfreitassc.backend.models.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(UserEntity userEntity);
    UserEntity toEntity(UserDTO userDTO);

    UserResponseDTO toResponseDto(UserEntity userEntity);
}
