package ru.neocode.neocode.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleRequest {

    private long userId;
    private String roleName;

}
