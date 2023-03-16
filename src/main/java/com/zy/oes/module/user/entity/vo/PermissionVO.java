package com.zy.oes.module.user.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PermissionVO
 * @author: MoZhu
 * @date: 2023/3/17 0:24
 * @description: <p> 权限VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("权限VO")
public class PermissionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限id")
    private Long id;

    @ApiModelProperty(value = "权限名称", required = true)
    private String permName;

    @ApiModelProperty("权限类型(0-菜单权限 1-数据权限)")
    private Integer type;
}
