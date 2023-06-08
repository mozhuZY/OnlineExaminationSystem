package com.zy.oes.module.question.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.zy.oes.common.base.entity.AbstractEntity;
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
public class JudgeQue extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者id
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 试题描述
     */
    @TableField("description")
    private String description;

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
     * 试题状态
     */
    @TableField("state")
    private Integer state;
}
