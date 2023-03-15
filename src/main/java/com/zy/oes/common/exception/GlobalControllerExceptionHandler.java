package com.zy.oes.common.exception;

import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GlobalControllerExceptionHandler
 * @author: MoZhu
 * @date: 2023/3/6 0:15
 * @description: <p> 统一异常处理 </p>
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * @title bindExceptionHandler
     * @description <p> 参数校验异常 </p>
     * @date 2023/3/6 0:18
     * @author MoZhu
     * @param e BindException对象
     * @return {@link ApiResult<Object>}
     */
    @ExceptionHandler(BindException.class)
    public ApiResult<Object> bindExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        // 这里需要打印日志
        LOGGER.error(e.getMessage(), e);
        return new ApiResult<>(ResultCode.VALIDATE_FAIL, error.getDefaultMessage());
    }

    /**
     * @title ApiExceptionHandler
     * @description <p> 应用异常 </p>
     * @date 2023/3/6 0:54
     * @author MoZhu
     * @param e 应用异常
     * @return {@link ApiResult<Object>}
     */
    @ExceptionHandler(ApiException.class)
    public ApiResult<Object> ApiExceptionHandler(ApiException e) {
        // 这里需要打印日志
        LOGGER.error(e.getMessage(), e);
        return new ApiResult<>(e.getCode(), e.getMsg(), e.getMessage());
    }

    /**
     * @title otherExceptionHandler
     * @description <p> 其他异常 </p>
     * @date 2023/3/15 2:38
     * @author MoZhu
     * @param e 其他异常
     * @return {@link ApiResult<Object>}
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<Object> otherExceptionHandler(Exception e) {
        // 这里需要打印日志
        LOGGER.error(e.getMessage(), e);
        return new ApiResult<>(500, e.getMessage(), null);
    }
}
