package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ChangePassword
 * @author: MoZhu
 * @date: 2023/3/14 0:47
 * @description: <p> 修改密码DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("修改密码DTO")
public class ChangePasswordDTO {

    @ApiModelProperty("旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @ApiModelProperty("新密码不能为空")
    @NotBlank
    @Size(min = 8, max = 16, message = "密码格式不正确")
    private String newPassword;
}
