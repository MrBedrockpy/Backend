package ru.neocode.neocode.service;

import ru.neocode.neocode.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.dto.response.LinkSessionResponse;
import ru.neocode.neocode.response.ApiResponse;

public interface UserSocialLinksService {

    ApiResponse<LinkSessionResponse> createSession(CreateLinkSessionRequest request);

    ApiResponse<Void> confirmSession(ConfirmLinkSessionRequest request);

}
