package org.project.service.impl;

import lombok.extern.log4j.Log4j2;
import org.project.entity.UserDataEntity;
import org.project.exception.NotFoundException;
import org.project.exception.UserException;
import org.project.repository.UserRepository;
import org.project.service.UserService;
import org.project.service.dto.ResponseEntityDTO;
import org.project.service.dto.UserDTO;
import org.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.project.util.Util.modelMapperUtil;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> getUser(String email) {
        UserDataEntity userData = userRepository.findByEmail(email);
        if (userData == null) {
            throw new NotFoundException("Error al recuperar el usuario: " + email);
        }
        UserDTO userDTO = Util.createObjectUserDTO(userData);
        return buildResponse("user ok", userDTO);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> createUser(UserDTO data) {
        if (userRepository.existsByEmail(data.getEmail())) {
            throw new UserException("El usuario ya existe: " + data.getEmail());
        }
        UserDataEntity newUserData = modelMapperUtil().map(data, UserDataEntity.class);
        UserDataEntity savedUserData = userRepository.save(newUserData);
        return buildResponse("user create ok", modelMapperUtil().map(savedUserData, UserDTO.class));
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> updateUser(UserDTO data) {
        UserDataEntity existingUser = userRepository.findByEmail(data.getEmail());
        if (existingUser == null) {
            throw new UserException("El usuario no existe: " + data.getEmail());
        }
        modelMapperUtil().map(data, existingUser);
        UserDataEntity savedUserData = userRepository.save(existingUser);
        return buildResponse("user update ok", modelMapperUtil().map(savedUserData, UserDTO.class));
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> deleteUser(UUID id) {
        Optional<UserDataEntity> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            return buildResponse("delete ok id: " + id, existingUser.get());
        } else {
            return buildResponse("user not exist: " + id, null);
        }
    }

    private ResponseEntity<ResponseEntityDTO> buildResponse(String message, Object data) {
        return ResponseEntity.ok(ResponseEntityDTO.builder().message(message).data(data).build());
    }


}
