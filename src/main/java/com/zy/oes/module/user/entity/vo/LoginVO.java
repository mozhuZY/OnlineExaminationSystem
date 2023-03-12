package com.zy.oes.module.user.entity.vo;

import com.zy.oes.module.user.entity.User;
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

    /**
     * token
     */
    @ApiModelProperty("token")
    private String token;

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

    public LoginVO(String token, User user) {
        this.token = token;
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
