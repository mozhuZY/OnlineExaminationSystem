package com.zy.oes.module.paper.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: RemoveQuestionDTO
 * @author: MoZhu
 * @date: 2023/3/19 15:08
 * @description: <p>  </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("删除试卷试题DTO")
public class RemovePaperQuestionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷id", required = true)
    @NotNull
    private Long paperId;

    @ApiModelProperty(value = "试题id", required = true)
    @NotNull
    private Long queId;

    @ApiModelProperty(value = "试题类型", required = true)
    @NotNull
    private Integer type;
}
