package ru.neocode.neocode.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.user.dto.response.RegionSettingsResponse;
import ru.neocode.neocode.user.entity.RegionSettings;
import ru.neocode.neocode.user.repository.RegionSettingsRepo;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.RegionSettingsService;

@Service
@RequiredArgsConstructor
public class RegionSettingsServiceImpl implements RegionSettingsService {

    private final RegionSettingsRepo repository;

    @Override
    public ApiResponse<RegionSettingsResponse> findById(long id) {
        return this.repository.findById(id).map(RegionSettings::toDto).map(ApiResponse::success)
                .orElse(ApiResponse.error(ApiError.notFound("User region settings not found")));
    }

    @Override
    public ApiResponse<Void> save(RegionSettings settings) {
        this.repository.save(settings);
        return ApiResponse.success(null);
    }
}
