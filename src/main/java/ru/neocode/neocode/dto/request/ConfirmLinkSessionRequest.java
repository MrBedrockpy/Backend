package ru.neocode.neocode.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.neocode.neocode.entity.LinkSession;

@Data
@AllArgsConstructor
public class ConfirmLinkSessionRequest {

    private final String sessionId;
    private final long telegramId;

}
