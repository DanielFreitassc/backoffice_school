package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;

import com.danielfreitassc.backend.dtos.CourseAvailableDTO;
import com.danielfreitassc.backend.dtos.CourseDTO;
import com.danielfreitassc.backend.dtos.CourseResponseDTO;
import com.danielfreitassc.backend.models.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDto(CourseEntity courseEntity);
    CourseResponseDTO toResponse(CourseEntity courseEntity);
    CourseAvailableDTO toAvailabble(CourseEntity courseEntity);
    CourseEntity toEntity(CourseDTO courseDTO);
}
