package com.zy.oes.module.exam.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: EnrollVO
 * @author: MoZhu
 * @date: 2023/3/21 14:57
 * @description: <p> 报名信息VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("报名信息VO")
public class EnrollVO implements Serializable {

    @ApiModelProperty("考试信息")
    private StudentExamVO exam;

    @ApiModelProperty("报名时间")
    private Date enrollTime;

    @ApiModelProperty("报名状态")
    private Integer state;
}
