package com.zy.oes.module.exam.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyExamDTO
 * @author: MoZhu
 * @date: 2023/3/18 18:20
 * @description: <p> 修改考试DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("修改考试DTO")
public class ModifyExamDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "试卷id")
    private Long paperId;

    @ApiModelProperty("考试名称")
    private String examName;

    @ApiModelProperty("考试类型")
    private Integer category;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releasedTime;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty("考试状态")
    private Integer state;
}
