package com.zy.oes.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "perm_id", type = IdType.AUTO)
    private Long permId;

    /**
     * 权限名称
     */
    @TableField("perm_name")
    private String permName;

    /**
     * 权限类型(0-菜单权限 1-数据权限)
     */
    @TableField("type")
    private Integer type;

    /**
     * 创建日期
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建用户id
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新日期
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 更新用户id
     */
    @TableField("update_user")
    private Long updateUser;

    /**
     * 逻辑删除标志
     */
    @TableField("is_del")
    private Boolean isDel;


}
