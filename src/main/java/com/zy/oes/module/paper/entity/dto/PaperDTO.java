package com.zy.oes.module.paper.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PaperDTO
 * @author: MoZhu
 * @date: 2023/3/19 11:53
 * @description: <p> 试卷DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("试卷DTO")
public class PaperDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试卷标题")
    private String title;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("试卷总分")
    private BigDecimal totalScore;
}
