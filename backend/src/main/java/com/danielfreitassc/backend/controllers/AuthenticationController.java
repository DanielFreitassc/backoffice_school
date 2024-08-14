package com.danielfreitassc.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.AuthenticationDTO;
import com.danielfreitassc.backend.dtos.LoginResponseDTO;
import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.infra.security.TokenService;
import com.danielfreitassc.backend.models.UserEntity;
import com.danielfreitassc.backend.repositories.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {
        UserDetails userDetails = userRepository.findByUsername(data.username());
        if (!(userDetails instanceof UserEntity)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessageDTO("Usuário não encontrado."));
        }

        UserEntity user = (UserEntity) userDetails;
        if(user.isAccountLocked()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessageDTO("A conta está bloqueada. Por favor, tente novamente mais tarde."));
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken(user);
            user.resetLoginAttempts();
            userRepository.save(user);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            user.incrementLoginAttempts();
            if(user.getLoginAttempts() >= 4) {
                user.lockAccount();
            }
            userRepository.save(user);
            int remainingAttempts = 4 - user.getLoginAttempts();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessageDTO("Senha incorreta: " + remainingAttempts));
        }
    }
}   
