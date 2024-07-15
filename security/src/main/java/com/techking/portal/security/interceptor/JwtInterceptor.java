package com.techking.portal.security.interceptor;

import com.techking.portal.core.result.Result;
import com.techking.portal.core.utils.ResponseUtil;
import com.techking.portal.core.utils.SpringUtil;
import com.techking.portal.security.SecurityConstant;
import com.techking.portal.security.UsersContextHolder;
import com.techking.portal.security.annotation.UrlPass;
import com.techking.portal.security.result.ResultSecurityCode;
import com.techking.portal.security.service.impl.userService.LoginDto;
import com.techking.portal.security.utils.AnnotationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    private static final RedisTemplate redisTemplate = SpringUtil.getBean(RedisTemplate.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (AnnotationUtil.containAnnotation(UrlPass.class, handler)) {
            return true;
        }
        String jwtHeader = request.getHeader(SecurityConstant.JwtInterceptor.AUTH_HEADER);
        if (StringUtils.isEmpty(jwtHeader) || !redisTemplate.hasKey(jwtHeader)) {
            ResponseUtil.writeResponse(response, Result.error(ResultSecurityCode.AUTHENTICATION_FAILED));
            return false;
        }
        setUserContextHolder(jwtHeader);
        return true;
    }

    private void setUserContextHolder(String jwtHeader) {
        LoginDto loginDto = (LoginDto) redisTemplate.opsForValue().get(jwtHeader);
        UsersContextHolder.setUserId(loginDto.getId());
        UsersContextHolder.setUsername(loginDto.getUsername());
        UsersContextHolder.setToken(loginDto.getJwt());
        UsersContextHolder.setPermissionCodes(loginDto.getPermissionCodes());
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UsersContextHolder.clear();
    }
}
