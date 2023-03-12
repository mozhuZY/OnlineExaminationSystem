package com.zy.oes.config.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.token.Token;
import com.zy.oes.common.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class LoginVerificationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader(Token.HEADER);
        // 拦截请求：未携带token
        if (token == null) {
            response.getWriter().write(JSONObject.toJSONString(new ApiResult<String>(ErrorCode.AUTHENTICATION_ERROR, "登录验证失败，请重新登录")));
            return false;
        }
        // 拦截请求：不存在或已失效的token
        if (tokenUtil.getUser(new Token(token)) == null) {
            response.getWriter().write(JSONObject.toJSONString(new ApiResult<String>(ErrorCode.AUTHENTICATION_ERROR, "不存在或已失效的token")));
            return false;
        }
        return true;
    }
}
