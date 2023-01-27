package com.zy.oes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 21:05
 * description:    试卷实体类
 */
@Data
public class Paper implements Serializable {
    /**
     * 试卷ID
     */
    private Integer paperId;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 发布者
     */
    private Integer creator;

    /**
     * 所属科目
     */
    private String subject;

    /**
     * 题目总数
     */
    private Integer questionNum;

    /**
     * 试卷总分
     */
    private BigDecimal totalScore;

    /**
     * 单选题id列表
     */
    private String qSc;

    /**
     * 多选题id列表
     */
    private String qMc;

    /**
     * 判断题id列表
     */
    private String qJudge;

    /**
     * 填空题id列表
     */
    private String qFill;

    /**
     * 主观题id列表
     */
    private String qInput;

    private static final long serialVersionUID = 1L;
}