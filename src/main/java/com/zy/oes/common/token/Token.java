package com.zy.oes.common.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ToKen
 * @author: MoZhu
 * @date: 2023/3/10 1:14
 * @description: <p>  </p>
 */
@Data
@ApiModel("Token")
public class Token implements Serializable {

    /**
     * 请求头
     */
    public static final String HEADER = "user-token";

    /**
     * token有效时间（四小时）
     */
    public static final long ALIVE = 14400;

    /**
     * token字符串
     */
    @ApiModelProperty("token字符串")
    private String token;

    public Token(String str) {
        this.token = str;
    }
}
