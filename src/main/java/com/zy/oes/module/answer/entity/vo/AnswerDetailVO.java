package com.zy.oes.module.answer.entity.vo;

import com.zy.oes.module.answer.entity.SingleAnswer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: AnswerVO
 * @author: MoZhu
 * @date: 2023/3/21 13:39
 * @description: <p> 答卷VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("答卷VO")
public class AnswerDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("答卷id")
    private Long id;

    @ApiModelProperty("考试id")
    private Long examId;

    @ApiModelProperty("考生id")
    private Long examineeId;

    @ApiModelProperty("答题列表")
    private List<SingleAnswer> answers;

    @ApiModelProperty("答卷总分")
    private BigDecimal totalScore;
}
