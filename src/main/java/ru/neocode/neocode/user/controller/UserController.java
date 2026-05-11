package ru.neocode.neocode.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.user.dto.request.ChangeRoleRequest;
import ru.neocode.neocode.user.dto.response.UserResponse;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<UserResponse>> findUserById(@RequestParam(name = "id") long id) {
        return this.userService.findById(id).toEntity();
    }

    @GetMapping(params = "name")
    public ResponseEntity<ApiResponse<UserResponse>> findUserByUsername(@RequestParam(name = "name") String username) {
        return this.userService.findByUsername(username).toEntity();
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @PostMapping("change-role")
    public ResponseEntity<ApiResponse<Void>> changeRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        return this.userService.changeRole(changeRoleRequest).toEntity();
    }
}
