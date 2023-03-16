package com.zy.oes.module.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: RoleVO
 * @author: MoZhu
 * @date: 2023/3/15 17:30
 * @description: <p> 角色VO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("角色VO")
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "上级角色id")
    private Long pre;
}
