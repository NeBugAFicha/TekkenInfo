package com.TekkenInfo.Domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role{
    USER(Set.of("OnlyUsersStuff")), ADMIN(Set.of("AllStuff"));
    private final Set<String> permissions;

    Role(Set<String> permissions){
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public  Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission))
                .collect(Collectors.toSet());
    }

}
