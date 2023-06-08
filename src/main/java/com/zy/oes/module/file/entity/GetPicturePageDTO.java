package com.zy.oes.module.file.entity;

import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetPicturePageDTO
 * @author: MoZhu
 * @date: 2023/5/6 1:01
 * @description: <p> 分页查询图片条件DTO </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询图片条件DTO")
public class GetPicturePageDTO extends PageDTO implements Serializable {

    @ApiModelProperty("图片id")
    private Long id;

    @ApiModelProperty("图片类型")
    private Integer type;

    @ApiModelProperty("图片状态")
    private Integer state;
}
