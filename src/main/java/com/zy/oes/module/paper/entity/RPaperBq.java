package com.zy.oes.module.paper.entity;

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
 * 试卷-填空题关系表
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("r_paper_bq")
@ApiModel(value="RPaperBq对象", description="试卷-填空题关系表")
public class RPaperBq extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "试卷id")
    @TableField("p_id")
    private Long pId;

    @ApiModelProperty(value = "试题id")
    @TableField("q_id")
    private Long qId;

    @ApiModelProperty(value = "试题序号")
    @TableField("q_no")
    private Integer qNo;

    @ApiModelProperty(value = "试题分数")
    @TableField("score")
    private Double score;
}
