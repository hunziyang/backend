package com.techking.portal.security;

import java.util.Set;

public class UsersContextHolder {

    private static final ThreadLocal<Long> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> username = new ThreadLocal<>();
    private static final ThreadLocal<String> token = new ThreadLocal<>();
    private static final ThreadLocal<Set<String>> permissionCodes = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        UsersContextHolder.userId.set(userId);
    }

    public static Long getUserId() {
        return UsersContextHolder.userId.get();
    }

    public static void setUsername(String username) {
        UsersContextHolder.username.set(username);
    }

    public static String getUsername() {
        return UsersContextHolder.username.get();
    }

    public static void setToken(String token) {
        UsersContextHolder.token.set(token);
    }

    public static String getToken() {
        return UsersContextHolder.token.get();
    }

    public static void setPermissionCodes(Set<String> permissionCodes) {
        UsersContextHolder.permissionCodes.set(permissionCodes);
    }

    public static Set<String> getPermissionCodes() {
        return UsersContextHolder.permissionCodes.get();
    }

    public static void clear() {
        userId.remove();
        username.remove();
        token.remove();
        permissionCodes.remove();
    }
}
