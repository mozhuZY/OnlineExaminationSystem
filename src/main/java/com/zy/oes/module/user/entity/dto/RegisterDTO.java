package com.zy.oes.module.user.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: RegisterDTO
 * @author: MoZhu
 * @date: 2023/4/28 3:11
 * @description: <p> 注册信息DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("注册信息DTO")
public class RegisterDTO implements Serializable {

    @ApiModelProperty("用户名")
    @NotNull
    private String username;

    @ApiModelProperty("用户姓名")
    @NotNull
    private String realName;

    @ApiModelProperty("密码")
    @NotNull
    private String password;

    @ApiModelProperty("邮箱")
    @NotNull
    private String email;

    @ApiModelProperty("身份")
    @NotNull
    private Integer role;

    @ApiModelProperty("uuid")
    @NotNull
    private String uuid;

    @ApiModelProperty("uuid")
    @NotNull
    private String verifyCode;
}
