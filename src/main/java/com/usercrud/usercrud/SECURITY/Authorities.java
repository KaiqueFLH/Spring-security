package com.usercrud.usercrud.SECURITY;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Authorities implements GrantedAuthority {

    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private final String name;

    @Override
    public String getAuthority() {
        return name();
    }
}
