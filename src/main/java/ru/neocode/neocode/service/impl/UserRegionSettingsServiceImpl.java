package ru.neocode.neocode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.repository.UserRegionSettingsRepo;
import ru.neocode.neocode.service.UserRegionSettingsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegionSettingsServiceImpl implements UserRegionSettingsService {

    private final UserRegionSettingsRepo repository;

    @Override
    public Optional<UserRegionSettings> findUserRegionSettingsById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public boolean save(UserRegionSettings settings) {
        if (this.repository.existsById(settings.getId())) return false;
        this.repository.save(settings);
        return true;
    }
}
