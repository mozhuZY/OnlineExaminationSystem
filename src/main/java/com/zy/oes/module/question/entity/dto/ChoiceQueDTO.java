package com.zy.oes.module.question.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ChoiceQueDTO
 * @author: MoZhu
 * @date: 2023/3/20 15:06
 * @description: <p> 选择题DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("选择题DTO")
public class ChoiceQueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试题描述")
    private String description;

    @ApiModelProperty("标准答案")
    private String stdAns;

    @ApiModelProperty("试题标签")
    private List<String> tags;

    @ApiModelProperty("试题选项")
    private List<String> opts;
}
