package com.tsypk.sniper.security;

/**
 * @author tsypk on 07.02.2022 13:50
 * @project sniper
 */
public enum Permission {
    POINTS_READ("points:read"),
    POINTS_WRITE("points:write"),
    POINTS_DELETE("points:delete"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    USERS_DELETE("users:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
