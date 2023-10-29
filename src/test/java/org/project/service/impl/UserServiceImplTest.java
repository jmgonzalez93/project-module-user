package org.project.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.config.ApplicationProperties;
import org.project.entity.PhoneEntity;
import org.project.entity.UserDataEntity;
import org.project.exception.NotFoundException;
import org.project.repository.UserRepository;
import org.project.service.dto.ResponseEntityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    ApplicationProperties applicationProperties;

    @Test
    void getUserOk() {
        when(userRepository.findByEmail(any())).thenReturn(getUserDataEntity());
        ResponseEntity<ResponseEntityDTO> response = userServiceImpl.getUser("correo");
        Assertions.assertEquals(response.getStatusCodeValue(), 200);
    }

    @Test
    void getUserError() {
        when(userRepository.findByEmail(any())).thenReturn(null);
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () ->
                userServiceImpl.getUser("correo"));
        Assertions.assertEquals(notFoundException.getMessage(), "User: correo does not exist");
    }


    private UserDataEntity getUserDataEntity() {
        UserDataEntity userData = UserDataEntity.builder()
                .id(UUID.randomUUID())
                .email("jonatan@gmail.com")
                .isActive(true)
                .build();
        userData.setPhones(List.of(getPhoneEntity(userData)));
        return userData;
    }

    private PhoneEntity getPhoneEntity(UserDataEntity userData) {
        return PhoneEntity.builder()
                .user(userData)
                .citycode("1")
                .countrycode("1")
                .number("+5692323")
                .build();
    }
}