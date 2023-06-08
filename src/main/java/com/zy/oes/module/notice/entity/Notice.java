package com.zy.oes.module.notice.entity;

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
@TableName("notice")
@ApiModel(value="Notice对象")
public class Notice extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "公告内容")
    @TableField("content")
    private String content;
}
