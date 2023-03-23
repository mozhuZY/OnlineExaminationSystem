package com.zy.oes.module.answer.entity.dto;

import com.zy.oes.module.answer.entity.SingleAnswer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyAnswerDTO
 * @author: MoZhu
 * @date: 2023/3/23 18:56
 * @description: <p> 修改答卷DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("修改答卷DTO")
public class ModifyAnswerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("答卷id")
    private Long id;

    @ApiModelProperty("答题序号列表")
    private List<Integer> qNos;

    @ApiModelProperty("答题分数列表")
    private List<Double> scores;
}
