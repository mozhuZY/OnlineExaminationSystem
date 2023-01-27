package com.zy.oes.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 21:05
 * description:    教师实体类
 */
@Data
public class Teacher implements Serializable {
    /**
     * 教师id
     */
    private Integer teacherId;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 所属组织机构
     */
    private String org;

    private static final long serialVersionUID = 1L;
}