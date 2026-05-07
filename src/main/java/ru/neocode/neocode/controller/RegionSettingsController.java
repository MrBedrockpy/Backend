package ru.neocode.neocode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.service.UserRegionSettingsService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region-settings")
public class RegionSettingsController {

    private final UserRegionSettingsService service;

    @GetMapping(params = "id")
    public ResponseEntity<UserRegionSettings> findRegionSettingsById(@RequestParam(name = "id") long id) {
        Optional<UserRegionSettings> userRegionSettings = this.service.findUserRegionSettingsById(id);
        return userRegionSettings.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
