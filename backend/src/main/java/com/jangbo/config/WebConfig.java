package com.jangbo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*");
        registry.addMapping("/**")
                //.allowedOriginPatterns("")
                .allowedOriginPatterns("*")
                .maxAge(3600)
                .allowedMethods("OPTIONS","POST","PATCH","GET","PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true);
    }





}
