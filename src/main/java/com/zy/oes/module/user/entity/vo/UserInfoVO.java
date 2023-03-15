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
 * @className: UserInfoVo
 * @author: MoZhu
 * @date: 2023/3/14 1:26
 * @description: <p> 用户基本VO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("用户基本VO")
public class UserInfoVO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户id")
    private String email;

    @ApiModelProperty("用户姓名")
    private String realName;

    @ApiModelProperty("用户年龄")
    private Integer age;

    @ApiModelProperty("用户性别")
    private Integer gender;

    @ApiModelProperty("用户所属组织")
    private String org;

    public UserInfoVO(User user, UserInfo userInfo) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.realName = userInfo.getRealName();
        this.age = userInfo.getAge();
        this.gender = userInfo.getGender();
        this.org = userInfo.getOrg();
    }
}
