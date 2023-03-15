package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: RoleDTO
 * @author: MoZhu
 * @date: 2023/3/15 16:14
 * @description: <p> 角色DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("角色DTO")
public class RoleDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank
    private String roleName;

    @ApiModelProperty(value = "上级角色id")
    private Long pre;
}
