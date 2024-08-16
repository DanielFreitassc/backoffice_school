package com.danielfreitassc.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.CourseAvailableDTO;
import com.danielfreitassc.backend.dtos.CourseDTO;
import com.danielfreitassc.backend.dtos.CourseResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.services.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createCourse(@RequestBody @Valid CourseDTO courseDTO) {
        return courseService.create(courseDTO);
    }

    @GetMapping
    public List<CourseResponseDTO> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("{id}")
    public CourseResponseDTO getById(@PathVariable UUID id) {
        return courseService.getById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> updateCourse(@PathVariable UUID id, @RequestBody @Valid CourseDTO courseDTO) {
        return courseService.update(id, courseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCourse(@PathVariable UUID id) {
        return courseService.delete(id);
    }

    @PatchMapping("available/{id}")
    public ResponseEntity<ResponseMessageDTO> setAvailable(@PathVariable UUID id, @RequestBody @Valid CourseAvailableDTO courseDTO) {
        return courseService.setAvailable(id, courseDTO);
    }
}
