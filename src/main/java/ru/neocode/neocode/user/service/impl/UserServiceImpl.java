package ru.neocode.neocode.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.neocode.neocode.user.dto.*;
import ru.neocode.neocode.user.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.user.dto.request.LoginRequest;
import ru.neocode.neocode.user.dto.request.RegisterRequest;
import ru.neocode.neocode.user.dto.response.AuthResponse;
import ru.neocode.neocode.user.dto.response.UserResponse;
import ru.neocode.neocode.user.entity.User;
import ru.neocode.neocode.user.entity.RegionSettings;
import ru.neocode.neocode.user.repository.UserRepo;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.security.JwtUtil;
import ru.neocode.neocode.user.service.RegionSettingsService;
import ru.neocode.neocode.user.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.util.HashUtil;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final RegionSettingsService regionSettingsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }

    @Override
    public ApiResponse<AuthResponse> login(LoginRequest request) {
        try {
            AuthenticationManager authManager =
                    authenticationConfiguration.getAuthenticationManager();
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));
            UserDetails user = (UserDetails) auth.getPrincipal();
            if (user == null) return ApiResponse.error(ApiError.notFound("User not found"));
            return ApiResponse.success(new AuthResponse(jwtUtil.generateToken(
                    user.getUsername(), user.getAuthorities())));
        } catch (Exception e) {
            return ApiResponse.error(ApiError.unauthorized("Invalid credentials"));
        }
    }

    @Override
    public ApiResponse<AuthResponse> register(RegisterRequest request) {
        if (this.repository.existsByUsername(request.getUsername())) return ApiResponse.error(ApiError.badRequest("User with this name already exists"));
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setReferralCode(HashUtil.generateHash(10));
        this.repository.save(user);
        user = this.repository.findByUsername(user.getUsername()).orElse(null);
        if (user == null) return ApiResponse.error(ApiError.internal("Registered user not found"));
        this.regionSettingsService.save(new RegionSettings(user.getId(),
                request.getCountry(), request.getTimeZoneId(), request.getCurrency()));
        return this.login(new LoginRequest(user.getUsername(), request.getPassword()));
    }

    @Override
    public ApiResponse<UserResponse> findById(long id) {
        return this.repository.findById(id).map(UserResponse::from).map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("User not found")));
    }

    @Override
    public ApiResponse<UserResponse> findByUsername(String username) {
        return this.repository.findByUsername(username).map(UserResponse::from).map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("User not found")));
    }

    @Override
    public ApiResponse<Void> changeRole(ChangeRoleRequest request) {
        User user = this.repository.findById(request.getUserId()).orElse(null);
        if (user == null) return ApiResponse.error(ApiError.notFound("User not found"));
        Role role = Role.getByName(request.getRoleName());
        if (role == null) return ApiResponse.error(ApiError.notFound("Role not found"));
        if (user.getRole().equals(role)) return ApiResponse.error(ApiError.badRequest("Role already set"));
        user.setRole(role);
        this.repository.save(user);
        return ApiResponse.success(null);
    }
}
