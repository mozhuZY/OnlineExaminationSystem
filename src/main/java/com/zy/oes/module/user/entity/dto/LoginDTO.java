package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: LoginDTO
 * @author: MoZhu
 * @date: 2023/3/11 2:06
 * @description: <p> 登录DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("登录DTO")
public class LoginDTO implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank
    private String password;
}
