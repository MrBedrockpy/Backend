package ru.neocode.neocode.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.neocode.neocode.dto.*;
import ru.neocode.neocode.dto.request.LoginRequest;
import ru.neocode.neocode.dto.request.RegisterRequest;
import ru.neocode.neocode.entity.User;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.repository.UserRepo;
import ru.neocode.neocode.security.JwtUtil;
import ru.neocode.neocode.service.UserRegionSettingsService;
import ru.neocode.neocode.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public String login(LoginRequest request) {
        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) auth.getPrincipal();
        return this.jwtUtil.generateToken(user.getUsername(), user.getAuthorities());
    }

    @Override
    public boolean register(RegisterRequest request) {
        if (this.repository.existsByUsername(request.getUsername())) return false;
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        this.repository.save(user);
        this.findUserByUsername(user.getUsername()).ifPresent(registredUser -> this.userRegionSettingsService.save(new UserRegionSettings(
                registredUser.getId(), request.getCountry(), request.getTimeZoneId(), request.getCurrency()
        )));
        return true;
    }

    @Override
    public Optional<User> findUserById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public boolean changeRole(User user, Role role) {
        if (user.getRole().equals(role)) return false;
        user.setRole(role);
        this.repository.save(user);
        return true;
    }
}
