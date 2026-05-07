package ru.neocode.neocode.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_region_settings")
public class UserRegionSettings {

    @Id
    @Column(unique = true, nullable = false)
    private long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private byte timeZoneId;

    @Column(nullable = false)
    private String currency;

}
