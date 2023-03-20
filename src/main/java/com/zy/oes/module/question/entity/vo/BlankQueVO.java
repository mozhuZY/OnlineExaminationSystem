package com.zy.oes.module.question.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: BlankQueVO
 * @author: MoZhu
 * @date: 2023/3/19 13:38
 * @description: <p> 填空题VO </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("填空题VO")
public class BlankQueVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "填空题id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty("创建者id")
    private Long creatorId;

    @ApiModelProperty("试题描述")
    private String description;

    @ApiModelProperty("标准答案")
    private String stdAns;

    @ApiModelProperty("试题标签")
    private List<String> tags;

    @ApiModelProperty("填空位置")
    private List<String> spaces;
}
