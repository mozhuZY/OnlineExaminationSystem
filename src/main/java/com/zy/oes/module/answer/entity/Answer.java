package com.zy.oes.module.answer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zy.oes.common.base.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
    private Long examId;

    /**
     * 考生id
     */
    @TableField("examinee_id")
    private Long examineeId;

    /**
     * 答卷内容文件路径
     */
    @TableField("content_file")
    private String contentFile;

    /**
     * 答卷总分
     */
    @TableField("total_score")
    private BigDecimal totalScore;
}
