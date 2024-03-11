package com.usercrud.usercrud.SECURITY;

import com.usercrud.usercrud.User.Model.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    private final User usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return usuario.getUserDetailsENTITY().getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUserDetailsENTITY().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
