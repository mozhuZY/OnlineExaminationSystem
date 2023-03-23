package com.zy.oes.module.answer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: SingleAnswer
 * @author: MoZhu
 * @date: 2023/3/21 13:44
 * @description: <p> 单个答题 </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("单个答题")
public class SingleAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("答题序号")
    private Integer no;

    @ApiModelProperty("试题id")
    private Long qId;

    @ApiModelProperty("答题内容")
    private String content;

    @ApiModelProperty("答题得分")
    private Double score;
}
