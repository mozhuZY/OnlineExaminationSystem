package com.zy.oes.module.exam.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @className: ExamDTO
 * @author: MoZhu
 * @date: 2023/3/18 13:50
 * @description: <p> 考试DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("考试DTO")
public class ExamDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
