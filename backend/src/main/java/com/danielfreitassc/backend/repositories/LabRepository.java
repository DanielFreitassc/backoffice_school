package com.danielfreitassc.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.LabEntity;

public interface LabRepository extends JpaRepository<LabEntity, UUID> {
    
}
