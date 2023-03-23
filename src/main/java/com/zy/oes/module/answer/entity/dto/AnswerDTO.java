package com.zy.oes.module.answer.entity.dto;

import com.zy.oes.module.answer.entity.SingleAnswer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: AnswerDTO
 * @author: MoZhu
 * @date: 2023/3/21 13:58
 * @description: <p> 答卷DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("答卷DTO")
public class AnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long examId;

    @ApiModelProperty(value = "答题列表", required = true)
    @NotEmpty
    private List<SingleAnswer> answers;
}
