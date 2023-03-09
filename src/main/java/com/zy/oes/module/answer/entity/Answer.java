package com.zy.oes.module.answer.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.zy.oes.common.base.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("answer")
public class Answer extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试id
     */
    @TableField("exam_id")
    private Integer examId;

    /**
     * 答卷内容文件路径
     */
    @TableField("content_file")
    private String contentFile;

    /**
     * 得分列表
     */
    @TableField("score")
    private String score;

    /**
     * 答卷总分
     */
    @TableField("total_score")
    private BigDecimal totalScore;
}
