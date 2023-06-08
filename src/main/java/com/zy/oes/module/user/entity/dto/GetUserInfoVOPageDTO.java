package com.zy.oes.module.user.entity.dto;

import com.zy.oes.common.base.entity.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: GetUserInfoVOPageDTO
 * @author: MoZhu
 * @date: 2023/5/5 18:52
 * @description: <p> 条件查询用户DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("登录VO")
public class GetUserInfoVOPageDTO extends PageDTO implements Serializable {

    @ApiModelProperty("状态")
    private Integer state;
}
