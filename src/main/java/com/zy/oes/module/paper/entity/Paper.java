package com.zy.oes.module.paper.entity;

import java.math.BigDecimal;
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
 * 试卷表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("paper")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试卷id
     */
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

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
