package com.zy.oes.module.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.zy.oes.common.base.entity.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 考试表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("exam")
public class Exam extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("paper_id")
    private Long paperId;

    /**
     * 考试名称
     */
    @TableField("exam_name")
    private String examName;

    /**
     * 考试类型
     */
    @TableField("category")
    private Integer category;

    /**
     * 学科
     */
    @TableField("subject")
    private String subject;

    /**
     * 发布时间
     */
    @TableField("released_time")
    private Date releasedTime;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 考试状态
     */
    @TableField("state")
    private Integer state;
}
