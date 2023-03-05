package com.zy.oes.common.response;

/**
 * @projectName: OnlineExaminationSystem
 * @interfaceName: StatusCode
 * @author: MoZhu
 * @date: 2023/3/5 23:38
 * @description: <p> 状态码 </p>
 */
public interface StatusCode {

    /**
     * @title getCode
     * @description <p> 获取状态码 </p>
     * @date 2023/3/5 23:50
     * @author MoZhu
     * @return {@link int}
     */
    int getCode();

    /**
     * @title getMsg
     * @description <p> 获取响应信息 </p>
     * @date 2023/3/5 23:52
     * @author MoZhu
     * @return {@link String}
     */
    String getMsg();
}
