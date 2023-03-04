package com.zy.oes.module.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 考试id
     */
    @TableId("exam_id")
    private Integer examId;

    /**
     * 试卷id
     */
    @TableField("paper_id")
    private Integer paperId;

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
