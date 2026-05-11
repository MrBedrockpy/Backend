package ru.neocode.neocode.user.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.neocode.neocode.user.dto.response.RegionSettingsResponse;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_region_settings")
public class RegionSettings {

    @Id
    @Column(unique = true, nullable = false)
    private long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private byte timeZoneId;

    @Column(nullable = false)
    private String currency;

    public RegionSettingsResponse toDto() {
        return new RegionSettingsResponse(id, country, timeZoneId, currency);
    }
}
