package com.techking.portal.security.mvc;

import com.techking.portal.security.interceptor.JwtInterceptor;
import com.techking.portal.security.interceptor.PermissionInterceptorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityWebConfig implements WebMvcConfigurer {

    private static final List<String> SWAGGER_PATH = Arrays.asList(
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/v2/api-docs/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(SWAGGER_PATH);
        registry.addInterceptor(new PermissionInterceptorHandler())
                .addPathPatterns("/**")
                .excludePathPatterns(SWAGGER_PATH);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger增加url映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
