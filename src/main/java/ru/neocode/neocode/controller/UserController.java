package ru.neocode.neocode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.dto.Role;
import ru.neocode.neocode.entity.User;
import ru.neocode.neocode.service.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping(params = "id")
    public Optional<User> findUserById(@RequestParam(name = "id") long id) {
        return this.userService.findUserById(id);
    }

    @GetMapping(params = "name")
    public Optional<User> findUserByUsername(@RequestParam(name = "name") String username) {
        return this.userService.findUserByUsername(username);
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @PostMapping("change-role/{id}/{role}")
    public ResponseEntity<String> changeRole(@PathVariable("id") long id, @PathVariable(name = "role") String roleName) {
        User user = this.userService.findUserById(id).orElse(null);
        if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        Role role = Role.getByName(roleName);
        if (role == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        boolean changed = this.userService.changeRole(user, role);
        if (changed) return ResponseEntity.ok("Role has been changed successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This role has already been changed");
    }
}
