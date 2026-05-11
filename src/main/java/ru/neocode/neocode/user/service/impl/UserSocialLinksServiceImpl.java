package ru.neocode.neocode.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neocode.neocode.user.dto.request.ConfirmLinkSessionRequest;
import ru.neocode.neocode.user.dto.request.CreateLinkSessionRequest;
import ru.neocode.neocode.user.dto.response.LinkSessionResponse;
import ru.neocode.neocode.user.entity.LinkSession;
import ru.neocode.neocode.user.entity.UserSocialLinks;
import ru.neocode.neocode.user.repository.LinkSessionRepo;
import ru.neocode.neocode.user.repository.UserSocialLinksRepo;
import ru.neocode.neocode.util.response.ApiError;
import ru.neocode.neocode.util.response.ApiResponse;
import ru.neocode.neocode.user.service.UserSocialLinksService;

@Service
@RequiredArgsConstructor
public class UserSocialLinksServiceImpl implements UserSocialLinksService {

    private final LinkSessionRepo sessionRepository;
    private final UserSocialLinksRepo repository;

    @Override
    public ApiResponse<LinkSessionResponse> createSession(CreateLinkSessionRequest request) {
        UserSocialLinks userSocialLinks = this.repository.findById(request.getUserId()).orElse(null);
        if (userSocialLinks == null) return ApiResponse.error(ApiError.notFound("The user with this id not exists!"));
        if (userSocialLinks.getTelegramId() != null) return ApiResponse.error(ApiError
                .badRequest("A user with this id already has a telegram!"));
        if (this.sessionRepository.existsById(request.getUserId())) return ApiResponse.error(
                ApiError.badRequest("The user with this id already create link session!"));
        LinkSession session = request.toEntity();
        this.sessionRepository.save(session);
        return ApiResponse.success(new LinkSessionResponse(session.getSessionId()));
    }

    @Override
    public ApiResponse<Void> confirmSession(ConfirmLinkSessionRequest request) {
        LinkSession session = this.sessionRepository.findBySessionId(request.getSessionId()).orElse(null);
        if (session == null) return ApiResponse.error(ApiError.notFound("The session with this id not exists!"));
        UserSocialLinks userSocialLinks = this.repository.findById(session.getUserId()).orElse(null);
        if (userSocialLinks == null) return ApiResponse.error(ApiError.internal("The user with this id not exists!"));
        if (this.repository.existsByTelegramId(request.getTelegramId())) return ApiResponse
                .error(ApiError.badRequest("This telegram is already use other user!"));
        this.sessionRepository.delete(session);
        userSocialLinks.setTelegramId(request.getTelegramId());
        this.repository.save(userSocialLinks);
        return ApiResponse.success(null);
    }
}
