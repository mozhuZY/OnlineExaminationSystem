package com.zy.oes.common.response;

import lombok.Getter;

/**
 * @projectName: OnlineExaminationSystem
 * @enumName: ErrorCode
 * @author: MoZhu
 * @date: 2023/3/6 0:38
 * @description: <p>  </p>
 */
@Getter
public enum ErrorCode implements StatusCode{
    // 2000 - 2099 -> 系统异常
    APP_ERROR(2000, "业务异常"),
    AUTHENTICATION_ERROR(2001, "认证异常"),
    // 2100 - 2199 -> 用户模块异常
    LOGIN_ERROR(2100, "登录异常");
    // 2200 - 2299 -> 考试模块异常
    // 2300 - 2399 -> 试卷模块异常
    // 2400 - 2499 -> 试题模块异常
    // 2500 - 2599 -> 答卷模块异常

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
