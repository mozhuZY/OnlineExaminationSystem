package com.zy.oes.module.question.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: QueVO
 * @author: MoZhu
 * @date: 2023/3/23 14:13
 * @description: <p> 试题VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("试题VO")
public class QueVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试题id")
    @NotNull
    private Long id;

    @ApiModelProperty("创建者id")
    private Long creatorId;

    @ApiModelProperty("试题描述")
    private String description;

    @ApiModelProperty("标准答案")
    private String stdAns;

    @ApiModelProperty("试题分数")
    private Double score;

    @ApiModelProperty("试题标签")
    private List<String> tags;
}
