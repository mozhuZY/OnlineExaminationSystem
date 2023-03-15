package com.zy.oes.common.base.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: PageDTO
 * @author: MoZhu
 * @date: 2023/3/15 3:08
 * @description: <p> 分页查询DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("分页查询DTO")
public class PageDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页码", required = true)
    @NotNull
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示条数", required = true)
    @NotNull
    private Integer pageSize;
}
