package com.zy.oes.module.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ModifyUserDTO
 * @author: MoZhu
 * @date: 2023/5/5 20:18
 * @description: <p> 修改用户DTO </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("修改用户DTO")
public class ModifyUserDTO implements Serializable {

    @ApiModelProperty("用户id")
    @NotNull
    private Long id;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户状态")
    private Integer state;
}
