package com.zy.oes.module.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.zy.oes.common.base.entity.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zy.oes.common.base.entity.EntityState;
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
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("r_exam_examinee")
@ApiModel(value = "RExamExaminee对象")
public class RExamExaminee extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private static List<EntityState> STATES = getState();

    @ApiModelProperty(value = "考试id")
    @TableField("exam_id")
    private Long examId;

    @ApiModelProperty(value = "考生id")
    @TableField("examinee_id")
    private Long examineeId;

    @ApiModelProperty(value = "报名状态")
    @TableField("state")
    private Integer state;

    private static List<EntityState> getState() {
        List<EntityState> stateList = new ArrayList<>();
        stateList.add(new EntityState(1, "已报名"));
        stateList.add(new EntityState(0, "已取消"));
        return stateList;
    }
}
