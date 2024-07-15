package com.techking.portal.security.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

public class AnnotationUtil {

    public static <T extends Annotation> boolean containAnnotation(Class<T> annotationClass, Object handle) {
        if (!(handle instanceof HandlerMethod)) {
            return false;
        }
        Annotation annotation = ((HandlerMethod) handle).getMethodAnnotation(annotationClass);
        if (ObjectUtils.isEmpty(annotation)) {
            return false;
        }
        return true;
    }
}
