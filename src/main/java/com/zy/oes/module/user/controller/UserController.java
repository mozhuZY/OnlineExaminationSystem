package com.zy.oes.module.user.controller;


import com.zy.oes.common.token.Token;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/user/user")
public class UserController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TokenUtil tokenUtil;

    @ApiOperation("测试Swagger")
    @GetMapping("/getTest")
    public User getTestString() {
        User user = new User();
        user.setId(1111L);
        user.setUsername("zy");
        user.setPassword("123456");
        return user;
    }

    /**
     * @title login
     * @description <p> 登录接口 </p>
     * @date 2023/3/11 2:12
     * @author MoZhu
     * @param loginDTO 登录DTO对象
     * @return {@link LoginVO}
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public LoginVO login(@RequestBody @Validated LoginDTO loginDTO) {
        LOGGER.info(loginDTO.toString());
        User user = new User();
        user.setId(1111L);
        user.setUsername("zy");
        user.setPassword("123456");
        Token token = tokenUtil.setToken(user);
        return new LoginVO(token.getToken(), user);
    }
}
