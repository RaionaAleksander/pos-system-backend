package com.aleksander.pos.dto.user;

import com.aleksander.pos.entity.enums.Role;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;
    private String password;
    private Role role;
}