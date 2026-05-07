package ru.neocode.neocode.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neocode.neocode.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.service.UserSocialLinksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social-link")
public class SocialLinkController {

    private final UserSocialLinksService service;

    @PostMapping("/telegram/link")
    public ResponseEntity<String> linkTelegram(@RequestBody CreateLinkSessionRequest request) {
        return this.service.createSession(request);
    }

    @PostMapping("/telegram/confirm")
    public ResponseEntity<String> confirmTelegram(@RequestBody ConfirmLinkSessionRequest request) {
        return this.service.confirmSession(request);
    }
}
