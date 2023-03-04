package com.zy.oes.module.user.controller;


import com.zy.oes.module.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user/user")
public class UserController {

    @ApiOperation("测试Swagger")
    @GetMapping("/getTest")
    public User getTestString() {
        User user = new User();
        user.setUserId(1111L);
        user.setUsername("zy");
        user.setPassword("123456");
        return user;
    }
}
