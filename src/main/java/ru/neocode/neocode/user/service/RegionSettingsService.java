package ru.neocode.neocode.user.service;

import ru.neocode.neocode.user.dto.response.RegionSettingsResponse;
import ru.neocode.neocode.user.entity.RegionSettings;
import ru.neocode.neocode.util.response.ApiResponse;

public interface RegionSettingsService {

    ApiResponse<RegionSettingsResponse> findById(long id);

    ApiResponse<Void> save(RegionSettings settings);

}
