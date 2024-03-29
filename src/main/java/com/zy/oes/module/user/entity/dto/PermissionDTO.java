package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PermissionDTO
 * @author: MoZhu
 * @date: 2023/3/16 1:12
 * @description: <p> 权限DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限DTO")
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限名称", required = true)
    private String permName;

    @ApiModelProperty("权限类型(0-菜单权限 1-数据权限)")
    private Integer type;
}
