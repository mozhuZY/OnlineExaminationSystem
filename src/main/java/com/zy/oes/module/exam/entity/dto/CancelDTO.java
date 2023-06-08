package com.zy.oes.module.exam.entity.dto;

import com.zy.oes.common.base.entity.Ids;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: CancelDTO
 * @author: MoZhu
 * @date: 2023/3/21 14:49
 * @description: <p> 取消报名DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("取消报名DTO")
public class CancelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试id", required = true)
    @NotNull
    private Long examId;
}
