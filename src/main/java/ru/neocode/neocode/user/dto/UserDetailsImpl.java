package ru.neocode.neocode.user.dto;

import lombok.Data;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import ru.neocode.neocode.user.entity.User;

import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + user.getRole().name());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}