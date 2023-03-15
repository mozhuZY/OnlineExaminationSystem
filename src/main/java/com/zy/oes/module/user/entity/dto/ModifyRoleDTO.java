package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyRoleDTO
 * @author: MoZhu
 * @date: 2023/3/15 16:48
 * @description: <p> 修改角色DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("修改角色DTO")
public class ModifyRoleDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("上级角色id")
    private Long pre;
}
