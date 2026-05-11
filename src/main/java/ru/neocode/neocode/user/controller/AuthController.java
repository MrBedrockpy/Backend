package ru.neocode.neocode.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neocode.neocode.user.dto.response.AuthResponse;
import ru.neocode.neocode.user.dto.request.LoginRequest;
import ru.neocode.neocode.user.dto.request.RegisterRequest;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        return userService.login(request).toEntity();
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        return userService.register(request).toEntity();
    }
}