package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: UpdateRolePermissionDTO
 * @author: MoZhu
 * @date: 2023/3/17 2:02
 * @description: <p> 批量更新角色权限关系DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("批量更新角色权限关系DTO")
public class UpdateRolePermissionDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull
    private Long roleId;

    @ApiModelProperty(value = "权限id列表", required = true)
    @NotEmpty
    private List<Long> permissionId;
}
