package com.zy.oes.module.question.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: JudgeQueVO
 * @author: MoZhu
 * @date: 2023/3/19 13:38
 * @description: <p> 判断题VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("判断题VO")
public class JudgeQueVO extends QueVO implements Serializable {
    private static final long serialVersionUID = 1L;
}
