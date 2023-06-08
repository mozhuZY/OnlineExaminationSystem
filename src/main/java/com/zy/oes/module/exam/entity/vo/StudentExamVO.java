package com.zy.oes.module.exam.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: StudentExamVO
 * @author: MoZhu
 * @date: 2023/5/3 16:17
 * @description: <p> 学生考试VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("学生考试VO")
public class StudentExamVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "试卷id", required = true)
    @NotNull
    private Long paperId;

    @ApiModelProperty("创建者id")
    private Long creatorId;

    @ApiModelProperty("创建者姓名")
    private String creatorName;

    @ApiModelProperty(value = "考试名称", required = true)
    @NotBlank
    private String examName;

    @ApiModelProperty(value = "考试类型", required = true)
    @NotNull
    private Integer category;

    @ApiModelProperty(value = "学科", required = true)
    @NotNull
    private String subject;

    @ApiModelProperty(value = "发布时间", required = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releasedTime;

    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "考试图片")
    private String picture;

    @ApiModelProperty(value = "考试状态")
    private Integer state;

    @ApiModelProperty("考生得分")
    private BigDecimal score;

    @ApiModelProperty("考试总分")
    private BigDecimal totalScore;
}
