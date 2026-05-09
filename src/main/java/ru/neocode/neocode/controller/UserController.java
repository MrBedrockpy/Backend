package ru.neocode.neocode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.dto.response.UserResponse;
import ru.neocode.neocode.entity.User;
import ru.neocode.neocode.response.ApiResponse;
import ru.neocode.neocode.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<UserResponse>> findUserById(@RequestParam(name = "id") long id) {
        return this.userService.findUserById(id).toEntity();
    }

    @GetMapping(params = "name")
    public ResponseEntity<ApiResponse<UserResponse>> findUserByUsername(@RequestParam(name = "name") String username) {
        return this.userService.findUserByUsername(username).toEntity();
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @PostMapping("change-role")
    public ResponseEntity<ApiResponse<Void>> changeRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        return this.userService.changeRole(changeRoleRequest).toEntity();
    }
}
