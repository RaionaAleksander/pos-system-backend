package com.aleksander.pos.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleksander.pos.dto.user.CreateUserRequest;
import com.aleksander.pos.entity.User;
import com.aleksander.pos.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole());
    }
}