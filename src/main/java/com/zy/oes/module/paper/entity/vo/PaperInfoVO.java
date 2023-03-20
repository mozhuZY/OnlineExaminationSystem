package com.zy.oes.module.paper.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PaperInfoVO
 * @author: MoZhu
 * @date: 2023/3/19 19:24
 * @description: <p> 试卷信息VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("试卷信息VO")
public class PaperInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷id")
    private Long id;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("试卷总分")
    private BigDecimal totalScore;
}
