package com.zy.oes.common.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: AbstractEntity
 * @author: MoZhu
 * @date: 2023/3/4 22:23
 * @description: <p> 抽象基础实体类 </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("抽象基础实体类")
public abstract class AbstractEntity implements Serializable {

    /**
     * 唯一标识id
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date updateTime;

    /**
     * 逻辑删除标志
     */
    @ApiModelProperty(value = "删除标识")
    @TableField(value = "is_del", fill = FieldFill.DEFAULT)
    @TableLogic
    @JsonIgnore
    protected Boolean isDel;
}
