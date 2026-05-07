package ru.neocode.neocode.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private String country;
    private byte timeZoneId;
    private String currency;

}