package ru.neocode.neocode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.user.entity.UserSocialLinks;

import java.util.Optional;

@Repository
public interface UserSocialLinksRepo extends JpaRepository<UserSocialLinks, Long> {

    Optional<UserSocialLinks> findByTelegramId(long telegramId);

    boolean existsByTelegramId(long telegramId);
}
