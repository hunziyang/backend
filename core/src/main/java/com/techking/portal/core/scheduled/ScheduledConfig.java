package com.techking.portal.core.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class ScheduledConfig {

    private static final String REQUEST_UUID = "requestUUID";

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void record(ProceedingJoinPoint joinPoint) throws Throwable {
        String uuid = UUID.randomUUID().toString().toUpperCase();
        MDC.put(REQUEST_UUID, uuid);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String name = methodSignature.getMethod().getName();
        log.warn("Scheduled {} is start", name);
        long startTime = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            log.warn("Scheduled {} is err", name, e);
        }
        long endTime = System.currentTimeMillis();
        log.warn("Scheduled {} is end,time:{}", name, endTime - startTime);
        MDC.remove(REQUEST_UUID);
        MDC.clear();
    }
}
