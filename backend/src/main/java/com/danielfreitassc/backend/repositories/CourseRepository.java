package com.danielfreitassc.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID>{
    
}
