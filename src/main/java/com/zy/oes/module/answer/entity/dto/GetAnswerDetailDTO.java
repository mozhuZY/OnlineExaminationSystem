package com.zy.oes.module.answer.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetAnswerDetailDTO
 * @author: MoZhu
 * @date: 2023/3/21 20:33
 * @description: <p> 查询答卷详细信息DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询答卷详细信息DTO")
public class GetAnswerDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long examId;

    @ApiModelProperty(value = "考生id", required = true)
    @NotNull
    private Long userId;
}
