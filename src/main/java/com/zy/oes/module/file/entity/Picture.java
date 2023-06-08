package com.zy.oes.module.file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zy.oes.common.base.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MoZhu
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("picture")
@ApiModel(value="Picture对象", description="")
public class Picture extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "图片名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "图片类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "图片状态")
    @TableField("state")
    private Integer state;
}
