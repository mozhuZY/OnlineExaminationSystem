package com.zy.oes.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * author:         MoZhu
 * date:           2023/1/27 01:15
 * description:    考生实体类
 */
@Data
public class Examinee implements Serializable {
    /**
     * 考生id
     */
    private Integer examineeId;

    /**
     * 考生姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    private static final long serialVersionUID = 1L;
}