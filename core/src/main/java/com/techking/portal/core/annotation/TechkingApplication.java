package com.techking.portal.core.annotation;

import com.techking.portal.Techking;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication(
        scanBasePackageClasses= Techking.class,
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public @interface TechkingApplication {
}
