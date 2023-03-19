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
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ExamVO
 * @author: MoZhu
 * @date: 2023/3/18 21:08
 * @description: <p> 考试VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("考试VO")
public class ExamVO implements Serializable {

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "试卷id", required = true)
    @NotNull
    private Long paperId;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releasedTime;

    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
