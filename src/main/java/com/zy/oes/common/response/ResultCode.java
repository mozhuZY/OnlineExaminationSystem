package com.zy.oes.common.response;

import lombok.Getter;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ResultCode
 * @author: MoZhu
 * @date: 2023/3/5 23:53
 * @description: <p> 返回码 </p>
 */
@Getter
public enum ResultCode implements StatusCode {
    SUCCESS(1000, "请求成功"),
    FAIL(1001, "请求失败"),
    VALIDATE_FAIL(1002, "参数校验失败"),
    RESPONSE_PACK_FAIL(1003, "response返回包装失败"),

    LOGIN_FAIL(2000, "登录失败"),

    QUERY_SUCCESS(3000, "查询成功"),
    QUERY_FAIL(3001, "查询失败"),
    ADD_SUCCESS(3100, "添加成功"),
    ADD_FAIL(3101, "添加失败"),
    REMOVE_SUCCESS(3200, "删除成功"),
    REMOVE_FAIL(3201, "删除失败"),
    UPDATE_SUCCESS(3300, "修改成功"),
    UPDATE_FAIL(3301, "修改失败");

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
