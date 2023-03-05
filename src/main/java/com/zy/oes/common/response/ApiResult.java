package com.zy.oes.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ApiResult
 * @author: MoZhu
 * @date: 2023/3/6 0:00
 * @description: <p> 统一请求返回 </p>
 */
@Data
public class ApiResult<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * @param code 状态码
     * @param msg 状态信息
     * @param data 响应数据
     * @title ApiResult
     * @description <p> 指定返回的状态码、状态信息、返回数据 </p>
     * @date 2023/3/6 0:09
     * @author MoZhu
     */
    public ApiResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @title ApiResult
     * @description <p> 默认成功只需返回响应数据 </p>
     * @date 2023/3/6 0:11
     * @author MoZhu
     * @param data 响应数据
     */
    public ApiResult(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * @title ApiResult
     * @description <p> 返回指定状态码 </p>
     * @date 2023/3/6 0:11
     * @author MoZhu
     * @param statusCode 状态码对象
     */
    public ApiResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }

    /**
     * @title ApiResult
     * @description <p> 返回指定状态码、响应数据 </p>
     * @date 2023/3/6 0:12
     * @author MoZhu
     * @param statusCode 状态码
     * @param data 响应数据
     */

    public ApiResult(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }
}
