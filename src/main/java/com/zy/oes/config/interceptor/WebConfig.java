package com.zy.oes.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    @Value("${oes.picture.base}")
    private String picturePath;

    @Autowired
    private LoginVerificationInterceptor loginVerificationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加登录验证拦截器
        registry.addInterceptor(loginVerificationInterceptor)
                // 添加拦截路径
                .addPathPatterns("/**")
                // 放行以下请求路径
                .excludePathPatterns("/api/user/user/login", "/api/user/user/register", "/api/user/user/sendVerifyCode", "/picture/**", "/api/exam/exam/enroll/get", "/api/file/picture/page/get")
                // 放行swagger和knife4j
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置拦截器访问静态资源

        // swagger静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 图片资源
        registry.addResourceHandler("/picture/**").addResourceLocations("file:" + picturePath);
    }
}
