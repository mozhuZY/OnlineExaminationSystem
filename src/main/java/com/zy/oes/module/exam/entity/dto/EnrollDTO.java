package com.zy.oes.module.exam.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: EnrollDTO
 * @author: MoZhu
 * @date: 2023/3/21 14:22
 * @description: <p> 考试报名DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("考试报名DTO")
public class EnrollDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long examId;
}
