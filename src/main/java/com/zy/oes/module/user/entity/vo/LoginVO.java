package com.zy.oes.module.user.entity.vo;

import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: LoginVO
 * @author: MoZhu
 * @date: 2023/3/11 2:16
 * @description: <p> 登录VO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("登录VO")
public class LoginVO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户姓名")
    private String realName;

    @ApiModelProperty("用户id")
    private String email;

    @ApiModelProperty("用户年龄")
    private Integer age;

    @ApiModelProperty("用户性别")
    private Integer gender;

    @ApiModelProperty("用户所属组织")
    private String org;

    @ApiModelProperty("用户头像")
    private String picture;

    @ApiModelProperty("用户角色")
    private Long role;
}
