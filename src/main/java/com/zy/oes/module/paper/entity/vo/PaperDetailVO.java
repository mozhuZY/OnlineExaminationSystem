package com.zy.oes.module.paper.entity.vo;

import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;
import com.zy.oes.module.question.entity.vo.SubQueVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PaperDetailVO
 * @author: MoZhu
 * @date: 2023/3/19 13:14
 * @description: <p> 试卷详细信息VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("试卷详细信息VO")
public class PaperDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷id")
    private Long id;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("试卷总分")
    private BigDecimal totalScore;

    @ApiModelProperty("选择题")
    private List<ChoiceQueVO> choiceQues;

    @ApiModelProperty("填空题")
    private List<BlankQueVO> blankQues;

    @ApiModelProperty("判断题")
    private List<JudgeQueVO> judgeQues;

    @ApiModelProperty("主观题")
    private List<SubQueVO> subQues;
}
