package org.project.controller;


import org.project.entity.UserDataEntity;
import org.project.service.dto.ResponseEntityDTO;
import org.project.service.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface ProjectApi {

    @GetMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<ResponseEntityDTO> getUser(@RequestParam(value = "email") String email);

    @PostMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<ResponseEntityDTO> createUser(@RequestBody UserDTO data);

    @PatchMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<ResponseEntityDTO> updateUser(@RequestBody UserDTO data);

    @DeleteMapping(value = "/user", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<ResponseEntityDTO> deleteUser(@RequestParam(value = "id") UUID id);
}
