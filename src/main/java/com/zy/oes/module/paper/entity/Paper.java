package com.zy.oes.module.paper.entity;

import java.math.BigDecimal;
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
 * 试卷表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("paper")
public class Paper extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建者id
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 学科
     */
    @TableField("subject")
    private String subject;

    /**
     * 试题数量
     */
    @TableField("q_num")
    private Integer qNum;

    /**
     * 试卷总分
     */
    @TableField("total_score")
    private BigDecimal totalScore;
}
