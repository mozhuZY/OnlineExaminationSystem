package com.zy.oes.module.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyPictureDTO
 * @author: MoZhu
 * @date: 2023/5/6 11:13
 * @description: <p> 修改图片信息DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("分页查询图片条件DTO")
public class ModifyPictureDTO implements Serializable {

    @ApiModelProperty(value = "图片id")
    private Long Id;

    @ApiModelProperty(value = "图片地址")
    private String path;

    @ApiModelProperty(value = "图片名称")
    private String name;

    @ApiModelProperty(value = "图片类型")
    private Integer type;

    @ApiModelProperty(value = "图片状态")
    private Integer state;
}
