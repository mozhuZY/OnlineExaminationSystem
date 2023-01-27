package com.zy.oes.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 21:05
 * description:    考试实体类
 */
@Data
public class Exam implements Serializable {
    /**
     * 考试id
     */
    private Integer examId;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 试卷ID
     */
    private Integer paperId;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 考试类型
     */
    private Integer category;

    /**
     * 科目
     */
    private String subject;

    /**
     * 发布时间
     */
    private Date releasedTime;

    /**
     * 考试开始时间
     */
    private Date startTime;

    /**
     * 考试结束时间
     */
    private Date endTime;

    /**
     * 发布者id
     */
    private Integer publisher;

    /**
     * 考试状态
     */
    private Integer state;

    private static final long serialVersionUID = 1L;
}