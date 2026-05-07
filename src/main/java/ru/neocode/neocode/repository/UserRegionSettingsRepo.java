package ru.neocode.neocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.entity.UserRegionSettings;

@Repository
public interface UserRegionSettingsRepo extends JpaRepository<UserRegionSettings, Long> {
}
