package com.zy.oes.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.zy.oes.common.base.BaseEntity;
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
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @NotNull
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

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
     * 性别
     */
    @ApiModelProperty("性别")
    @TableField("sex")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotNull
    @TableField("email")
    private String email;
}
