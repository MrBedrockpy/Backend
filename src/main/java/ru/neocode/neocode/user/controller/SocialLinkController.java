package ru.neocode.neocode.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neocode.neocode.user.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.user.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.user.dto.response.LinkSessionResponse;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.UserSocialLinksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social-link")
public class SocialLinkController {

    private final UserSocialLinksService service;

    @PostMapping("/telegram/link")
    public ResponseEntity<ApiResponse<LinkSessionResponse>> linkTelegram(@RequestBody CreateLinkSessionRequest request) {
        return this.service.createSession(request).toEntity();
    }

    @PostMapping("/telegram/confirm")
    public ResponseEntity<ApiResponse<Void>> confirmTelegram(@RequestBody ConfirmLinkSessionRequest request) {
        return this.service.confirmSession(request).toEntity();
    }
}
