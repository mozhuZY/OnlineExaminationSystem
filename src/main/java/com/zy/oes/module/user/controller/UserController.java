package com.zy.oes.module.user.controller;


import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.token.Token;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Autowired
    private IUserService service;

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
    public LoginVO login(@RequestBody @Valid LoginDTO loginDTO) throws ApiException {
        LOGGER.info(loginDTO.toString());
        User user = this.service.login(loginDTO);
        if (user == null) {
            throw new ApiException(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        }
        return new LoginVO(tokenUtil.setToken(user).getToken(), user);
    }

    /**
     * @title getUserByUserId
     * @description <p> 根据用户id查询用户详细信息 </p>
     * @date 2023/3/14 1:35
     * @author MoZhu
     * @param userId 用户id
     * @return {@link User}
     */
    @ApiOperation("根据用户id查询用户详细信息")
    @GetMapping("/get")
    public User getUserByUserId(@RequestParam("userId") Long userId) {
        return this.service.getUserByUserId(userId);
    }

    /**
     * @title addUser
     * @description <p> 注册用户 </p>
     * @date 2023/3/13 23:18
     * @author MoZhu
     * @param user 注册用户信息
     * @return {@link String}
     */
    @ApiOperation("注册用户")
    @PostMapping("/add")
    public String addUser(@RequestBody @Valid User user) throws ApiException {
        if (this.service.addUser(user) == 1) {
            return "添加成功";
        } else {
            throw new ApiException("注册失败");
        }
    }

    @ApiOperation("修改密码")
    @PostMapping("/modify/password")
    public String modifyPassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO, HttpServletRequest request) throws ApiException {
        Token token = new Token(request.getHeader(Token.HEADER));
        switch(this.service.changePassword(changePasswordDTO, token)) {
            case 1:
                return "修改成功";
            case 0:
                throw new ApiException(ResultCode.FAIL, "修改失败");
            case -1:
                throw new ApiException(ResultCode.FAIL, "旧密码必须正确");
            case -2:
                throw new ApiException(ResultCode.FAIL, "新旧密码不能相同");
            default:
                throw new ApiException(ErrorCode.APP_ERROR, "修改密码：未知错误");
        }
    }
}
