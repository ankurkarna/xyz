package org.xyz.CRUD.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xyz.CRUD.dto.UserRequestDto;
import org.xyz.CRUD.dto.UserResponseDto;
import org.xyz.CRUD.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto request) {
        try {
            userService.createUser(request);
            return ResponseEntity.ok("user created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while creating user: " + e.getMessage());
        }
    }


    @PutMapping("{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }


    @DeleteMapping("{id}")
    public UserResponseDto deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}