package ru.neocode.neocode.dto.request;

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
