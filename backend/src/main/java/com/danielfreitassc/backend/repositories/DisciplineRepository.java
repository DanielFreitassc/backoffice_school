package com.danielfreitassc.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.DisciplineEntity;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity,UUID>{
    
}
