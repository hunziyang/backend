package com.techking.portal.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.techking.portal.security.SecurityConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String sign(String username) {
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        Date expireDate = new Date(now + SecurityConstant.Jwt.EXPIRE_TIME);
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "hs256");
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstant.Jwt.SECRET);
        return JWT
                .create()
                .withHeader(header)
                .withSubject(username)
                .withIssuedAt(nowDate)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }
}
