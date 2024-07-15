package com.techking.portal.security;

public interface SecurityConstant {

    interface JwtInterceptor {
        String AUTH_HEADER = "Authorization";
    }

    interface Jwt {
        long EXPIRE_TIME = 8 * 60 * 60 * 1000;
        long EXPIRE_HOUR = 8L;
        String SECRET = "Techking";
    }

    interface Permission{
        Long ROOT_PARENT_ID = 0L;
    }
}
