package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyUserInfoDTO
 * @author: MoZhu
 * @date: 2023/3/15 3:15
 * @description: <p> 修改用户信息DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("修改用户信息DTO")
public class ModifyUserInfoDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("用户姓名")
    private String realName;

    @ApiModelProperty("用户年龄")
    private Integer age;

    @ApiModelProperty("用户性别")
    private Integer gender;

    @ApiModelProperty("用户所属组织")
    private String org;
}
