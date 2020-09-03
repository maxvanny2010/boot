package com.boot.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
public enum Role implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
