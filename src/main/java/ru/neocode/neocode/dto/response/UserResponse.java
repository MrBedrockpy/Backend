package ru.neocode.neocode.dto.response;

import lombok.Getter;
import ru.neocode.neocode.dto.Role;
import ru.neocode.neocode.entity.User;

@Getter
public class UserResponse {

    private final long id;
    private final Role role;
    private final String username;
    private final String email;
    private final String referralCode;

    private UserResponse(final long id, final Role role, final String username, final String email, final String referralCode) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.referralCode = referralCode;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getRole(), user.getUsername(),
                user.getEmail(), user.getReferralCode());
    }

}
