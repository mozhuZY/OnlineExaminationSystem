package com.zy.oes.module.user.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyPermission
 * @author: MoZhu
 * @date: 2023/3/16 1:27
 * @description: <p> 修改权限信息DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("修改权限信息DTO")
public class ModifyPermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "权限名称")
    private String permName;

    @ApiModelProperty("权限类型")
    private Integer type;
}
