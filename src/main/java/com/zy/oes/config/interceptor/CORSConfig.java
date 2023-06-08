package com.zy.oes.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: OnlineExaminationSystem
 * @className: CORSConfig
 * @author: MoZhu
 * @date: 2023/4/24 22:40
 * @description: <p> 跨域请求配置 </p>
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    // 允许的跨域请求
    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE", "OPTIONS"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowCredentials(true).allowedMethods(ORIGINS).maxAge(3600);
    }
}
