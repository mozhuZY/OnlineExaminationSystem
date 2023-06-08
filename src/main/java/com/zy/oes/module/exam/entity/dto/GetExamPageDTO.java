package com.zy.oes.module.exam.entity.dto;

import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetExamPageDTO
 * @author: MoZhu
 * @date: 2023/5/1 20:50
 * @description: <p> 条件获取考试分页信息DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("条件获取考试分页信息DTO")
public class GetExamPageDTO extends PageDTO implements Serializable {

    @ApiModelProperty("考试id")
    private Long id;

    @ApiModelProperty("考试名称")
    private String examName;

    @ApiModelProperty("考试类型")
    private String category;

    @ApiModelProperty("考试学科")
    private String subject;

    @ApiModelProperty("考试状态")
    private List<Integer> states;
}
