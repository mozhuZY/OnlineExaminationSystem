package com.zy.oes.common.util;

import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.response.StatusCode;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ApiUtil
 * @author: MoZhu
 * @date: 2023/3/14 0:14
 * @description: <p> api返回工具类 </p>
 */
@SuppressWarnings("javadoc")
public class ApiUtil<T> {

    /**
     * @title success
     * @description <p> 默认的成功返回方法 </p>
     * @date 2023/3/14 0:36
     * @author MoZhu
     * @param msg 返回信息
     * @return {@link ApiResult< String>}
     */
    public static ApiResult<String> success(String msg) {
        return new ApiResult<>(ResultCode.SUCCESS, msg);
    }

    /**
     * @title success
     * @description <p> 通用成功返回方法 </p>
     * @date 2023/3/14 0:18
     * @author MoZhu
     * @param code 状态码
     * @return {@link ApiResult<String>}
     */
    public static ApiResult<String> success(ResultCode code) {
        return new ApiResult<>(code);
    }

    /**
     * @title success
     * @description <p> 通用成功返回方法(返回数据) </p>
     * @date 2023/3/14 0:23
     * @author MoZhu
     * @param code 返回码
     * @param data 返回数据
     * @return {@link ApiResult<T>}
     */
    public static <T> ApiResult<T> success(ResultCode code, T data) {
        return new ApiResult<>(code, data);
    }

    /**
     * @title fail
     * @description <p> 默认的失败返回方法 </p>
     * @date 2023/3/14 0:37
     * @author MoZhu
     * @param msg 返回信息
     * @return {@link ApiResult<String>}
     */
    public static <T> ApiResult<String> fail(String msg) {
        return new ApiResult<>(msg);
    }

    /**
     * @title fail
     * @description <p> 通用失败返回方法 </p>
     * @date 2023/3/14 0:24
     * @author MoZhu
     * @param code 返回码
     * @return {@link ApiResult<String>}
     */
    public static <T> ApiResult<String> fail(ResultCode code) {
        return new ApiResult<>(code);
    }

    /**
     * @title fail
     * @description <p> 通用失败返回方法(返回具体信息) </p>
     * @date 2023/3/14 0:24
     * @author MoZhu
     * @param code 返回码
     * @param data 返回具体信息
     * @return {@link ApiResult<String>}
     */
    public static <T> ApiResult<String> fail(ResultCode code, String data) {
        return new ApiResult<>(code, data);
    }

    /**
     * @title error
     * @description <p> 通用错误返回方法 </p>
     * @date 2023/3/14 0:26
     * @author MoZhu
     * @param code 错误码
     * @return {@link ApiResult<String>}
     */
    public static <T> ApiResult<String> error(ErrorCode code) {
        return new ApiResult<>(code);
    }

    /**
     * @title error
     * @description <p> 通用错误返回方法(返回提示) </p>
     * @date 2023/3/14 0:27
     * @author MoZhu
     * @param code 错误码
     * @param data 返回提示信息
     * @return {@link ApiResult<String>}
     */
    public static <T> ApiResult<String> error(ErrorCode code, String data) {
        return new ApiResult<>(code, data);
    }
}
