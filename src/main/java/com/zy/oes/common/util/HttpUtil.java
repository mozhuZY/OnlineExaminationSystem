package com.zy.oes.common.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName: OnlineExaminationSystem
 * @className: HttpUtil
 * @author: MoZhu
 * @date: 2023/3/15 3:41
 * @description: <p> Http工具类 </p>
 */
@SuppressWarnings("javadoc")
public class HttpUtil {

    /**
     * @title getCurrentRequest
     * @description <p> 获取当前请求的HttpServletRequest对象 </p>
     * @date 2023/3/15 3:44
     * @author MoZhu
     * @param
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * @title getCurrentResponse
     * @description <p> 获取当前请求的HttpServletResponse对象 </p>
     * @date 2023/3/15 3:46
     * @author MoZhu
     * @param
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getCurrentResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getResponse();
    }
}
