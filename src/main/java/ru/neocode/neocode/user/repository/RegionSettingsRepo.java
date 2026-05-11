package ru.neocode.neocode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.user.entity.RegionSettings;

@Repository
public interface RegionSettingsRepo extends JpaRepository<RegionSettings, Long> {
}
