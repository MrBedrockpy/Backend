package ru.neocode.neocode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.response.ApiResponse;
import ru.neocode.neocode.service.UserRegionSettingsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region-settings")
public class RegionSettingsController {

    private final UserRegionSettingsService service;

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<UserRegionSettings>> findRegionSettingsById(@RequestParam(name = "id") long id) {
        return this.service.findUserRegionSettingsById(id).toEntity();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveRegionSettings(@RequestBody UserRegionSettings settings) {
        return this.service.save(settings).toEntity();
    }
}
