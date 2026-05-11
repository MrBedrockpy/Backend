package ru.neocode.neocode.user.service;

import ru.neocode.neocode.user.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.user.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.user.dto.response.LinkSessionResponse;
import ru.neocode.neocode.util.response.ApiResponse;

public interface UserSocialLinksService {

    ApiResponse<LinkSessionResponse> createSession(CreateLinkSessionRequest request);

    ApiResponse<Void> confirmSession(ConfirmLinkSessionRequest request);

}
