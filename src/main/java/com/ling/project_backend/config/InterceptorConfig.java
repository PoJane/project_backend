package com.ling.project_backend.config;

import com.ling.project_backend.intercepter.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * token拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    public InterceptorConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor=tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath=new ArrayList<>();
        //不需要拦截的url：/login和/home和/
        excludePath.add("/login");
        excludePath.add("/register");
        excludePath.add("/home");
        excludePath.add("/images");
        excludePath.add("/images/**");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","PATCH","OPTIONS","HEAD")
                .maxAge(3600*24);
    }
}
