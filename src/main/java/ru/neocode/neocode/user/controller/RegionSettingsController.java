package ru.neocode.neocode.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neocode.neocode.user.dto.response.RegionSettingsResponse;
import ru.neocode.neocode.user.entity.RegionSettings;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.RegionSettingsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region-settings")
public class RegionSettingsController {

    private final RegionSettingsService service;

    @GetMapping(params = "id")
    public ResponseEntity<ApiResponse<RegionSettingsResponse>> findRegionSettingsById(@RequestParam(name = "id") long id) {
        return this.service.findById(id).toEntity();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> saveRegionSettings(@RequestBody RegionSettings settings) {
        return this.service.save(settings).toEntity();
    }
}
