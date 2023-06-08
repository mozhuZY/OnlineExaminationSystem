package com.zy.oes.module.question.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetChoiceQuestionConditionDTO
 * @author: MoZhu
 * @date: 2023/5/4 18:56
 * @description: <p> 条件筛选获取选择题信息DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("条件筛选获取选择题信息DTO")
public class GetChoiceQuestionConditionDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试题id")
    private Long id;

    @ApiModelProperty("创建者id")
    private Long creatorId;

    @ApiModelProperty("试题标签")
    private List<String> tags;

    @ApiModelProperty("试题状态")
    private Integer state;
}
