package com.zy.oes.module.answer.entity.dto;

import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetAnswerDTO
 * @author: MoZhu
 * @date: 2023/3/21 19:22
 * @description: <p> 分页查询答卷DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页查询答DTO")
public class GetAnswerPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long examId;

    @ApiModelProperty(value = "考生id", required = true)
    @NotNull
    private Long userId;
}
