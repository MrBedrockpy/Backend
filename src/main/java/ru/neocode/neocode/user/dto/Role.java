package ru.neocode.neocode.user.dto;

public enum Role {

    DEVELOPER, USER;

    public static Role getByName(String name) {
        for (Role role : Role.values())
            if (role.name().equals(name)) return role;
        return null;
    }

}
