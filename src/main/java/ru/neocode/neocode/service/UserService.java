package ru.neocode.neocode.service;

import ru.neocode.neocode.dto.response.AuthResponse;
import ru.neocode.neocode.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.dto.request.LoginRequest;
import ru.neocode.neocode.dto.request.RegisterRequest;
import ru.neocode.neocode.dto.response.UserResponse;
import ru.neocode.neocode.entity.User;
import ru.neocode.neocode.response.ApiResponse;

public interface UserService {

    ApiResponse<AuthResponse> login(LoginRequest request);

    ApiResponse<AuthResponse> register(RegisterRequest request);

    ApiResponse<UserResponse> findUserById(long id);

    ApiResponse<UserResponse> findUserByUsername(String username);

    ApiResponse<Void> changeRole(ChangeRoleRequest request);

}
