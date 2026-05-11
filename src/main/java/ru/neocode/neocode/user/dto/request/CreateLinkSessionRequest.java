package ru.neocode.neocode.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.neocode.neocode.user.entity.LinkSession;
import ru.neocode.neocode.util.HashUtil;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkSessionRequest {

    private long userId;

    public LinkSession toEntity() {
        return new LinkSession(userId, HashUtil.generateHash(32));
    }
}
