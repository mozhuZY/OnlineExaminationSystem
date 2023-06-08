package com.zy.oes.module.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.zy.oes.common.base.entity.AbstractEntity;
import com.zy.oes.common.base.entity.EntityState;
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

    public static final EntityState[] states = getStates();

    /**
     * 试卷id
     */
    @TableField("paper_id")
    private Long paperId;

    /**
     * 创建者id
     */
    @TableField("creator_id")
    private Long creatorId;

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
     * 考试图片
     */
    @TableField("picture_id")
    private Long pictureId;

    /**
     * 考试状态
     */
    @TableField("state")
    private Integer state;

    private static EntityState[] getStates() {
        return new EntityState[]{
                new EntityState(0, "待审核"),
                new EntityState(1, "已发布"),
                new EntityState(2, "正在考试"),
                new EntityState(3, "已结束")
        };
    }
}
