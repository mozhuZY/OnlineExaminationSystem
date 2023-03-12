package com.zy.oes.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.zy.oes.common.base.entity.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "user",description = "User表实体类")
@TableName("user")
public class User extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotNull
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotNull
    @TableField("password")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotNull
    @TableField("email")
    private String email;
}
