package com.danielfreitassc.backend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.dtos.UserDTO;
import com.danielfreitassc.backend.dtos.UserResponseDTO;
import com.danielfreitassc.backend.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<ResponseMessageDTO>createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{username}")
    public UserResponseDTO getUserById(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PatchMapping("{username}")
    public ResponseEntity<ResponseMessageDTO> patchUser(@PathVariable String username, @RequestBody @Valid UserDTO userDTO) {
        return userService.patchUser(username, userDTO);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<ResponseMessageDTO>  deleteUser(@PathVariable String username) {
        return userService.delete(username);
    }
}
