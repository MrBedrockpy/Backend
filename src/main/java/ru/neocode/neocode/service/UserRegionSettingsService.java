package ru.neocode.neocode.service;

import ru.neocode.neocode.entity.UserRegionSettings;

import java.util.Optional;

public interface UserRegionSettingsService {

    Optional<UserRegionSettings> findUserRegionSettingsById(long id);

    boolean save(UserRegionSettings settings);

}
