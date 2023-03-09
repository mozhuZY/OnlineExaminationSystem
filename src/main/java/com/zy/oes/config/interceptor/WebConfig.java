package com.zy.oes.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: OnlineExaminationSystem
 * @className: WebConfig
 * @author: MoZhu
 * @date: 2023/3/10 1:03
 * @description: <p> Web设置 </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录验证拦截器
        registry.addInterceptor(new LoginVerificationInterceptor())
                // 添加拦截路径
                .addPathPatterns("/**")
                // 放行以下请求路径
                .excludePathPatterns("/api/user/user/login", "/doc.html");
    }
}
