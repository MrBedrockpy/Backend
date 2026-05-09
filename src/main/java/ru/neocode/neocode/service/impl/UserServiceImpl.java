package ru.neocode.neocode.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.neocode.neocode.dto.*;
import ru.neocode.neocode.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.dto.request.LoginRequest;
import ru.neocode.neocode.dto.request.RegisterRequest;
import ru.neocode.neocode.dto.response.AuthResponse;
import ru.neocode.neocode.entity.User;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.repository.UserRepo;
import ru.neocode.neocode.response.ApiError;
import ru.neocode.neocode.response.ApiResponse;
import ru.neocode.neocode.security.JwtUtil;
import ru.neocode.neocode.service.UserRegionSettingsService;
import ru.neocode.neocode.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRegionSettingsService userRegionSettingsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }

    @Override
    public ApiResponse<AuthResponse> login(LoginRequest request) {
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        return ApiResponse.success(new AuthResponse(this.jwtUtil.generateToken(user.getUsername(), user.getAuthorities())));
    }

    @Override
    public ApiResponse<AuthResponse> register(RegisterRequest request) {
        if (this.repository.existsByUsername(request.getUsername())) return ApiResponse.error(ApiError.badRequest("User with this name already exists"));
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        this.repository.save(user);
        user = this.findUserByUsername(user.getUsername()).getData();
        if (user == null) return ApiResponse.error(ApiError.internal("Registered user not found"));
        this.userRegionSettingsService.save(new UserRegionSettings(user.getId(),
                request.getCountry(), request.getTimeZoneId(), request.getCurrency()));
        return this.login(new LoginRequest(user.getUsername(), request.getPassword()));
    }

    @Override
    public ApiResponse<User> findUserById(long id) {
        return this.repository.findById(id).map(ApiResponse::success).orElse(ApiResponse.error(ApiError.notFound("User not found")));
    }

    @Override
    public ApiResponse<User> findUserByUsername(String username) {
        return this.repository.findByUsername(username).map(ApiResponse::success).orElse(ApiResponse.error(ApiError.notFound("User not found")));
    }

    @Override
    public ApiResponse<Void> changeRole(ChangeRoleRequest request) {
        User user = this.findUserById(request.getUserId()).getData();
        if (user == null) return ApiResponse.error(ApiError.notFound("User not found"));
        Role role = Role.getByName(request.getRoleName());
        if (role == null) return ApiResponse.error(ApiError.notFound("Role not found"));
        if (user.getRole().equals(role)) return ApiResponse.error(ApiError.badRequest("Role already set"));
        user.setRole(role);
        this.repository.save(user);
        return ApiResponse.success(null);
    }
}
