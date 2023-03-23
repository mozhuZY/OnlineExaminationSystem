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
 * @className: SubQueVO
 * @author: MoZhu
 * @date: 2023/3/19 13:38
 * @description: <p> 主观题VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("主观题VO")
public class SubQueVO extends QueVO implements Serializable {
    private static final long serialVersionUID = 1L;
}
