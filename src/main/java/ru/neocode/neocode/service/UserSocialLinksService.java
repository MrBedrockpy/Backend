package ru.neocode.neocode.service;

import org.springframework.http.ResponseEntity;
import ru.neocode.neocode.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.dto.request.CreateLinkSessionRequest;

public interface UserSocialLinksService {

    ResponseEntity<String> createSession(CreateLinkSessionRequest request);

    ResponseEntity<String> confirmSession(ConfirmLinkSessionRequest request);

}
