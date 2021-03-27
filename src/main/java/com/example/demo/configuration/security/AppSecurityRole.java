package com.example.demo.configuration.security;

import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.Getter;

@Getter
public enum AppSecurityRole {
    READER(List.of(AppSecurityPermission.LIST_USERS).toSet()),
    ADMIN(List.of(AppSecurityPermission.LIST_USERS,
            AppSecurityPermission.CREATE_USER).toSet());

    private final Set<AppSecurityPermission> setOfPermissions;

    AppSecurityRole(Set<AppSecurityPermission> permissions) {
        setOfPermissions = permissions;
    }
}
