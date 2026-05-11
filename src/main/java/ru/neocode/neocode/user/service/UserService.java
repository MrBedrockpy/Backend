package ru.neocode.neocode.user.service;

import ru.neocode.neocode.user.dto.response.AuthResponse;
import ru.neocode.neocode.user.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.user.dto.request.LoginRequest;
import ru.neocode.neocode.user.dto.request.RegisterRequest;
import ru.neocode.neocode.user.dto.response.UserResponse;
import ru.neocode.neocode.util.response.ApiResponse;

public interface UserService {

    ApiResponse<AuthResponse> login(LoginRequest request);

    ApiResponse<AuthResponse> register(RegisterRequest request);

    ApiResponse<UserResponse> findById(long id);

    ApiResponse<UserResponse> findByUsername(String username);

    ApiResponse<Void> changeRole(ChangeRoleRequest request);

}
