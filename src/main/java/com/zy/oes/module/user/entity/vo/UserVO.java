package com.zy.oes.module.user.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @projectName: OnlineExaminationSystem
 * @className: UserVo
 * @author: MoZhu
 * @date: 2023/3/14 1:26
 * @description: <p> 用户基本VO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("用户基本VO")
public class UserVO {

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户id")
    private String email;

    @ApiModelProperty("用户年龄")
    private Integer age;

    @ApiModelProperty("用户性别")
    private String gender;

    @ApiModelProperty("用户所属组织")
    private String org;
}
