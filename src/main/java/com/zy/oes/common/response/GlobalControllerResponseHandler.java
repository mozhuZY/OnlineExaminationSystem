package com.zy.oes.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GlobalControllerResponseHandler
 * @author: MoZhu
 * @date: 2023/3/6 0:21
 * @description: <p> 统一响应返回 </p>
 */
@RestControllerAdvice("com.zy.oes.module")
public class GlobalControllerResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是ApiResult类型或注释了NotControllerResponseHandle都不进行包装
        return !returnType.getParameterType().isAssignableFrom(ApiResult.class) ||
                !returnType.hasMethodAnnotation(NotControllerResponseHandle.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                // 将数据包装再ApiResult中在转换为json串返回
                return mapper.writeValueAsString(new ApiResult<>(data));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        // 否则直接包装成ApiResult返回
        return new ApiResult<>(data);
    }
}
