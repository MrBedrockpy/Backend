package ru.neocode.neocode.service;

import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.response.ApiResponse;

public interface UserRegionSettingsService {

    ApiResponse<UserRegionSettings> findUserRegionSettingsById(long id);

    ApiResponse<Void> save(UserRegionSettings settings);

}
