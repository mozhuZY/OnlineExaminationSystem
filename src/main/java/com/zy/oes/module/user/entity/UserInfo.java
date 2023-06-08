package com.zy.oes.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zy.oes.common.base.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_info")
@ApiModel(value="UserInfo对象", description="UserInfo对象")
public class UserInfo extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "性别")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty(value = "所属组织")
    @TableField("org")
    private String org;

    @ApiModelProperty(value = "用户头像id")
    @TableField("picture_id")
    private Long pictureId;
}
