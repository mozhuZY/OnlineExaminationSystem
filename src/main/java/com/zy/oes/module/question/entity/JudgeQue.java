package com.zy.oes.module.question.entity;

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
 * 判断题表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("judge_que")
public class JudgeQue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试题id
     */
    @TableId(value = "q_id", type = IdType.AUTO)
    private Integer qId;

    /**
     * 试题描述
     */
    @TableField("discript")
    private String discript;

    /**
     * 标准答案
     */
    @TableField("std_ans")
    private String stdAns;

    /**
     * 试题标签
     */
    @TableField("tags")
    private String tags;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建用户id
     */
    @TableField("create_user")
    private Long createUser;

    /**
     * 更新时间
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
