package com.zy.oes.module.answer.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 答案id
     */
    @TableId("ans_id")
    private Integer ansId;

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
