package com.techking.portal.security.interceptor;

import com.techking.portal.core.result.Result;
import com.techking.portal.core.utils.ResponseUtil;
import com.techking.portal.security.UsersContextHolder;
import com.techking.portal.security.annotation.TechkingPermission;
import com.techking.portal.security.result.ResultSecurityCode;
import com.techking.portal.security.utils.AnnotationUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class PermissionInterceptorHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!AnnotationUtil.containAnnotation(TechkingPermission.class, handler)) {
            return true;
        }
        TechkingPermission techkingPermission = (TechkingPermission)((HandlerMethod) handler).getMethodAnnotation(TechkingPermission.class);
        String permission = techkingPermission.value();
        Set<String> userPermissions = UsersContextHolder.getPermissionCodes();
        if (ObjectUtils.isEmpty(userPermissions) || !userPermissions.contains(permission)){
            ResponseUtil.writeResponse(response, Result.error(ResultSecurityCode.PERMISSION_DENIED));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
