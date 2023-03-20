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
 * @className: ChoiceQueVO
 * @author: MoZhu
 * @date: 2023/3/19 13:37
 * @description: <p> 选择题VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("选择题VO")
public class ChoiceQueVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "选择题id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty("创建者id")
    private Long creatorId;

    @ApiModelProperty("试题描述")
    private String description;

    @ApiModelProperty("标准答案")
    private String stdAns;

    @ApiModelProperty("试题标签")
    private List<String> tags;

    @ApiModelProperty("试题选项")
    private List<String> opts;
}
