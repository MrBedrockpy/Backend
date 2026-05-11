package ru.neocode.neocode.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neocode.neocode.user.entity.LinkSession;

import java.util.Optional;

@Repository
public interface LinkSessionRepo extends JpaRepository<LinkSession, Long> {

    Optional<LinkSession> findBySessionId(String sessionId);
}
