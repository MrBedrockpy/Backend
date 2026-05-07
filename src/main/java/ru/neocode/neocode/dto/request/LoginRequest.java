package ru.neocode.neocode.dto.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}