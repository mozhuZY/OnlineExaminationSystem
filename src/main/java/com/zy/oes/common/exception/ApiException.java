package com.zy.oes.common.exception;

import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.StatusCode;
import lombok.Getter;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ApiException
 * @author: MoZhu
 * @date: 2023/3/6 0:45
 * @description: <p>  </p>
 */
@Getter
public class ApiException extends RuntimeException {
    private final int code;
    private final String msg;

    /**
     * @title ApiException
     * @description <p> 手动设置异常 </p>
     * @date 2023/3/6 0:48
     * @author MoZhu
     * @param statusCode 状态码
     * @param message 异常信息
     */
    public ApiException(StatusCode statusCode, String message) {
        // message用户用户设置抛出错误详情
        super(message);
        // 状态码
        this.code = statusCode.getCode();
        // 状态信息
        this.msg = statusCode.getMsg();
    }

    /**
     * @title ApiException
     * @description <p> 默认异常使用APP_ERROR状态码 </p>
     * @date 2023/3/6 0:49
     * @author MoZhu
     * @param message 异常信息
     */
    public ApiException(String message) {
        super(message);
        this.code = ErrorCode.APP_ERROR.getCode();
        this.msg = ErrorCode.APP_ERROR.getMsg();
    }
}
