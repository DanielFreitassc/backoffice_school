package com.danielfreitassc.backend.services;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.danielfreitassc.backend.dtos.UserDTO;
import com.danielfreitassc.backend.models.UserRole;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer {

    private final UserService userService;
    
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void init() {
        try {
            userService.getByUsername(adminUsername);
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                UserDTO adminUserDTO = new UserDTO("Admin", adminUsername, adminPassword, UserRole.ADMIN);
                userService.create(adminUserDTO);
            }
        }
    }
}
