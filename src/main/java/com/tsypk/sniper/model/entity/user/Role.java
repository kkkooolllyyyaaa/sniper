package com.tsypk.sniper.model.entity.user;

import com.tsypk.sniper.security.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tsypk on 06.02.2022 18:12
 * @project sniper
 */
public enum Role {
    ROLE_ADMIN(Set.of(
            Permission.POINTS_READ, Permission.POINTS_WRITE, Permission.POINTS_DELETE,
            Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_DELETE
    )),

    ROLE_USER(Set.of(
            Permission.POINTS_READ, Permission.POINTS_WRITE, Permission.POINTS_DELETE,
            Permission.USERS_WRITE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
