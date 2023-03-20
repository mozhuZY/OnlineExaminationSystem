package com.zy.oes.module.paper.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyPaperInfoDTO
 * @author: MoZhu
 * @date: 2023/3/19 13:05
 * @description: <p> 修改试卷基本信息DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("修改试卷基本信息DTO")
public class ModifyPaperInfoDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("试卷总分")
    private BigDecimal totalScore;
}
