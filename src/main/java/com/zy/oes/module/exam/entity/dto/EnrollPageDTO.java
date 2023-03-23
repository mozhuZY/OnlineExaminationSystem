package com.zy.oes.module.exam.entity.dto;

import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: EnrollPageDTO
 * @author: MoZhu
 * @date: 2023/3/21 17:14
 * @description: <p>  </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("考试报名DTO")
public class EnrollPageDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id", required = true)
    @NotNull
    private Long userId;
}
