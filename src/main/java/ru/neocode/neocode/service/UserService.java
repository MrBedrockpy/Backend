package ru.neocode.neocode.service;

import ru.neocode.neocode.dto.request.LoginRequest;
import ru.neocode.neocode.dto.request.RegisterRequest;
import ru.neocode.neocode.dto.Role;
import ru.neocode.neocode.entity.User;

import java.util.Optional;

public interface UserService {

    String login(LoginRequest request);

    boolean register(RegisterRequest request);

    Optional<User> findUserById(long id);

    Optional<User> findUserByUsername(String username);

    boolean changeRole(User user, Role role);

}
