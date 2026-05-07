package ru.neocode.neocode.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.neocode.neocode.entity.LinkSession;
import ru.neocode.neocode.util.HashUtil;

@Data
@AllArgsConstructor
public class CreateLinkSessionRequest {

    private final long userId;

    public LinkSession toEntity() {
        return new LinkSession(userId, HashUtil.generateHash());
    }
}
