package ru.neocode.neocode.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.entity.LinkSession;
import ru.neocode.neocode.entity.UserSocialLinks;
import ru.neocode.neocode.repository.LinkSessionRepo;
import ru.neocode.neocode.repository.UserSocialLinksRepo;
import ru.neocode.neocode.service.UserSocialLinksService;
import ru.neocode.neocode.util.ResponsibleService;

@Service
@RequiredArgsConstructor
public class UserSocialLinksServiceImpl implements UserSocialLinksService, ResponsibleService {

    private final LinkSessionRepo sessionRepository;
    private final UserSocialLinksRepo repository;

    @Override
    public ResponseEntity<String> createSession(CreateLinkSessionRequest request) {
        UserSocialLinks userSocialLinks = this.repository.findById(request.getUserId()).orElse(null);
        if (userSocialLinks == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user with this id not exists!");
        if (userSocialLinks.getTelegramId() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A user with this id already has a telegram!");
        if (this.sessionRepository.existsById(request.getUserId())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user with this id already create link session!");
        LinkSession session = request.toEntity();
        this.sessionRepository.save(session);
        return getSuccess();
    }

    @Override
    public ResponseEntity<String> confirmSession(ConfirmLinkSessionRequest request) {
        LinkSession session = this.sessionRepository.findBySessionId(request.getSessionId()).orElse(null);
        if (session == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The session with this id not exists!");;
        UserSocialLinks userSocialLinks = this.repository.findById(session.getUserId()).orElse(null);
        if (userSocialLinks == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The user with this id not exists!");
        if (this.repository.existsByTelegramId(request.getTelegramId())) return ResponseEntity.badRequest().body("This telegram is already use other user!");
        this.sessionRepository.delete(session);
        userSocialLinks.setTelegramId(request.getTelegramId());
        this.repository.save(userSocialLinks);
        return getSuccess();
    }
}
