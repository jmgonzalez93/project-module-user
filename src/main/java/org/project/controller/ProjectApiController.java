package org.project.controller;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.project.service.UserService;
import org.project.service.dto.ResponseEntityDTO;
import org.project.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class ProjectApiController implements ProjectApi {

    private final UserService userService;

    @Autowired
    public ProjectApiController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> getUser(String email) {
        log.info("[GET] /api/v1/user  id: {}", email);
        return userService.getUser(email);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> createUser(UserDTO data) {
        log.info("[POST] /api/v1/user  data: {}", new Gson().toJson(data));
        return userService.createUser(data);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> updateUser(UserDTO data) {
        log.info("[POST] /api/v1/user  data: {}", new Gson().toJson(data));
        return userService.updateUser(data);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> deleteUser(UUID id) {
        log.info("[DELETE] /api/v1/user  id: {}", id);
        return userService.deleteUser(id);
    }

    @Override
    public ResponseEntity<ResponseEntityDTO> loginUser(String token, String email) {
        return null;
    }


}
