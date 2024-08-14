package com.danielfreitassc.backend.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.danielfreitassc.backend.models.UserEntity;
import com.danielfreitassc.backend.models.UserRole;


public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    UserDetails findByUsername(String username);
    // @Query("SELECT COUNT(u) FROM users u WHERE u.role = :role")
    // long countByRole(UserRole role);

    // @Query("SELECT p FROM users p ORDER BY create_at DESC")
    // Page<UserEntity> findAll(Pageable pageable);

    // @Query("SELECT p FROM users p WHERE p.role = :roles")
    // Page<UserEntity> findAllByRoles(UserRole roles, Pageable pageable);
}
