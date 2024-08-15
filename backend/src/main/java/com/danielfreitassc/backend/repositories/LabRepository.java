package com.danielfreitassc.backend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.LabEntity;

public interface LabRepository extends JpaRepository<LabEntity, UUID> {
    Optional<LabEntity> findByName(String name);
}
