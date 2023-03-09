package com.zy.oes.config.interceptor;

import com.zy.oes.config.token.Token;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName: OnlineExaminationSystem
 * @className: LoginVerificationInterceptor
 * @author: MoZhu
 * @date: 2023/3/10 0:41
 * @description: <p> 登录验证拦截器 </p>
 */
public class LoginVerificationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        String token = request.getHeader(Token.HEADER);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
