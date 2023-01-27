package com.zy.oes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 15:03
 * description:    答卷实体类
 */
@Data
public class Answer implements Serializable {
    /**
     * 答卷id
     */
    private Integer ans_id;

    /**
     * 考试id
     */
    private Integer exam_id;

    /**
     * 考生id
     */
    private Integer examinee_id;

    /**
     * 答卷内容
     */
    private String content;

    /**
     * 得分列表
     */
    private String score;

    /**
     * 总分
     */
    private BigDecimal total_score;

    private static final long serialVersionUID = 1L;
}