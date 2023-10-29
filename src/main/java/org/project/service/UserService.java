package org.project.service;

import org.project.service.dto.ResponseEntityDTO;
import org.project.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    ResponseEntity<ResponseEntityDTO> getUser(String email);

    ResponseEntity<ResponseEntityDTO> createUser(UserDTO userDTO);

    ResponseEntity<ResponseEntityDTO> updateUser(UserDTO user);

    ResponseEntity<ResponseEntityDTO> deleteUser(UUID id);

    ResponseEntity<ResponseEntityDTO> loginUser(String token, String email);
}
