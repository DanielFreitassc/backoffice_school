package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ResponseMessageDTO;
import com.danielfreitassc.backend.dtos.UserDTO;
import com.danielfreitassc.backend.dtos.UserResponseDTO;
import com.danielfreitassc.backend.mappers.UserMapper;
import com.danielfreitassc.backend.models.UserEntity;
import com.danielfreitassc.backend.repositories.UserQueryRepositoy;
import com.danielfreitassc.backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserQueryRepositoy userQueryRepositoy;

    public ResponseEntity<ResponseMessageDTO> create(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.username()) != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário já cadatrado");
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity.setPassword(encryptedPassword);
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessageDTO("Usuário criado com sucesso;"));
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toResponseDto).toList();
    }

    public UserResponseDTO getById(UUID id) {
        Optional<UserEntity> user = userQueryRepositoy.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário com este ID cadastrado;");
        return userMapper.toResponseDto(user.get());
    }

    public UserResponseDTO getByUsername(String username) {
        Optional<UserEntity> user = userQueryRepositoy.findByUsername(username);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário com este ID cadastrado;");
        return userMapper.toResponseDto(user.get());
    }

    public ResponseEntity<ResponseMessageDTO> patchUser(UUID id,  UserDTO userDTO) {
        Optional<UserEntity> userOptional = userQueryRepositoy.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado;");
        }
        
        UserEntity userEntity = userOptional.get();
        
        if (userDTO.name() != null && !userDTO.name().isBlank()) {
            userEntity.setName(userDTO.name());
        }
        if (userDTO.username() != null && !userDTO.username().isBlank()) {
            userEntity.setUsername(userDTO.username());
        }
        if (userDTO.role() != null) {
            userEntity.setRole(userDTO.role());
        }
        if (userDTO.password() != null && !userDTO.password().isBlank()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
            userEntity.setPassword(encryptedPassword);
        }
        userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Usuário atualizado com sucesso;"));
    }

    public ResponseEntity<ResponseMessageDTO> delete(UUID id) {
        Optional<UserEntity> user = userQueryRepositoy.findById(id);
        if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuário com este ID cadastrado;");
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageDTO("Usuário removido com sucesso;"));
    }
    
}
