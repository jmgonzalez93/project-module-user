package org.project.service.impl;

import lombok.extern.log4j.Log4j2;
import org.project.config.ApplicationProperties;
import org.project.entity.UserDataEntity;
import org.project.exception.NotAuthenticationException;
import org.project.exception.NotFoundException;
import org.project.exception.NotPermissionException;
import org.project.exception.UserException;
import org.project.repository.UserRepository;
import org.project.service.UserService;
import org.project.service.dto.ResponseEntityDTO;
import org.project.service.dto.UserDTO;
import org.project.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import static org.project.util.Util.*;


@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> getUser(String email) {
        UserDataEntity userData = userRepository.findByEmail(email);
        if (userData == null) {
            throw new NotFoundException(String.format("User: %s does not exist", email));
        }
        UserDTO userDTO = Util.createObjectUserDTO(userData);
        return buildResponse("user ok", userDTO);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> createUser(UserDTO data) {
        if (userRepository.existsByEmail(data.getEmail())) {
            throw new UserException("User already exists: " + data.getEmail());
        }
        if (!validPass(Pattern.compile(applicationProperties.getExRegPass()), data.getPassword()))
            throw new UserException("Password does not meet requirements\n: " + data.getPassword());

        UserDataEntity newUserData = modelMapperUtil().map(data, UserDataEntity.class);
        UserDataEntity savedUserData = userRepository.save(newUserData);
        return buildResponse("user create ok", modelMapperUtil().map(savedUserData, UserDTO.class));
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> updateUser(UserDTO data) {
        UserDataEntity existingUser = userRepository.findByEmail(data.getEmail());
        if (existingUser == null) {
            throw new UserException("User does not exist: " + data.getEmail());
        }
        existingUser.getPhones().clear();
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

    @Override
    public ResponseEntity<ResponseEntityDTO> loginUser(String token, String email) {
        UserDataEntity userData = userRepository.findByEmailAndToken(email, extractToken(token));
        if (userData == null)
            throw new NotAuthenticationException(String.format("User: %s does not exist or invalid token: %s", email, extractToken(token)));

        if (!userData.getIsActive())
            throw new NotPermissionException(String.format("user not activated: %s", email));

        LocalDateTime lastLogin = LocalDateTime.now();
        userRepository.updateBylastLogin(lastLogin, userData.getId());
        return buildResponse("Authorization OK", UserDTO.builder()
                .id(userData.getId())
                .lastLogin(lastLogin)
                .created(userData.getCreated())
                .modified(userData.getModified())
                .isActive(userData.getIsActive())
                .token(userData.getToken())
                .build());
    }

    private ResponseEntity<ResponseEntityDTO> buildResponse(String message, Object data) {
        return ResponseEntity.ok(ResponseEntityDTO.builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .data(data)
                .build());
    }


}
