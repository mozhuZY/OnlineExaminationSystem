package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: UpdateUserRolesDTO
 * @author: MoZhu
 * @date: 2023/3/17 1:44
 * @description: <p> 批量更新用户角色关系DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("批量更新用户角色关系DTO")
public class UpdateUserRolesDTO {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", required = true)
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "角色id列表", required = true)
    @NotEmpty
    private List<Long> roleIds;
}
