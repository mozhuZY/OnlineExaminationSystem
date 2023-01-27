package com.zy.oes.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 21:05
 * description:    题目实体类
 */
@Data
public class Question implements Serializable {
    /**
     * 题目ID
     */
    private Integer questionId;

    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 题目描述
     */
    private String discript;

    /**
     * 标准答案
     */
    private String stdAns;

    /**
     * 题目作者
     */
    private Integer creator;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 题目类型
     */
    private Integer categroy;

    /**
     * 题目标签列表
     */
    private String tags;

    /**
     * 提供选项
     */
    private String opts;

    /**
     * 填空位置列表
     */
    private String spaces;

    private static final long serialVersionUID = 1L;
}