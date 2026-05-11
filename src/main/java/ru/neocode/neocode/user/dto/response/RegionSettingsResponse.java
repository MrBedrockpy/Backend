package ru.neocode.neocode.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegionSettingsResponse {

    private final long id;
    private final String country;
    private final byte timeZoneId;
    private final String currency;

}
