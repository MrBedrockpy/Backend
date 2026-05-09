package ru.neocode.neocode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.entity.UserRegionSettings;
import ru.neocode.neocode.repository.UserRegionSettingsRepo;
import ru.neocode.neocode.response.ApiError;
import ru.neocode.neocode.response.ApiResponse;
import ru.neocode.neocode.service.UserRegionSettingsService;

@Service
@RequiredArgsConstructor
public class UserRegionSettingsServiceImpl implements UserRegionSettingsService {

    private final UserRegionSettingsRepo repository;

    @Override
    public ApiResponse<UserRegionSettings> findUserRegionSettingsById(long id) {
        return this.repository.findById(id).map(ApiResponse::success).orElse(ApiResponse.error(ApiError.notFound("User region settings not found")));
    }

    @Override
    public ApiResponse<Void> save(UserRegionSettings settings) {
        this.repository.save(settings);
        return ApiResponse.success(null);
    }
}
