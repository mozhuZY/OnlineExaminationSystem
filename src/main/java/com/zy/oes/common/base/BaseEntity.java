package com.zy.oes.common.base;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: BaseEntity
 * @author: MoZhu
 * @date: 2023/3/4 22:23
 * @description: <p> 基础实体类 </p>
 */
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    @TableField("create_time")
    protected Date createTime;

    /**
     * 创建用户id
     */
    @TableField("create_user")
    protected Long createUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    protected Date updateTime;

    /**
     * 更新用户id
     */
    @TableField("update_user")
    protected Long updateUser;

    /**
     * 逻辑删除标志
     */
    @TableField("is_del")
    protected Boolean isDel;
}
