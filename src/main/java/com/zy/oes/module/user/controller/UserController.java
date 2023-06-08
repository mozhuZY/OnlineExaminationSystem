package com.zy.oes.module.user.controller;


import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.token.Token;
import com.zy.oes.common.util.*;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.entity.dto.ModifyUserDTO;
import com.zy.oes.module.user.entity.dto.RegisterDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.service.IUserInfoService;
import com.zy.oes.module.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@SuppressWarnings("javadoc")
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
    private RedisUtil redisUtil;

    @Autowired
    private IUserInfoService userInfoService;

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
//        LOGGER.info(loginDTO.toString());
        LoginVO vo = this.service.login(loginDTO);
        if (vo == null) {
            throw new ApiException(ResultCode.LOGIN_FAIL, "用户名或密码错误");
        }
        return vo;
    }

    /**
     * @title logout
     * @description <p> 用户登出 </p>
     * @date 2023/4/25 1:41
     * @author MoZhu
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("登出")
    @GetMapping("/logout")
    public ApiResult<?> logout() {
        if (tokenUtil.destroyCurrentToken()) {
            return ApiUtil.success("已登出");
        }
        throw new ApiException(ResultCode.FAIL);
    }

    /**
     * @title detectLoginState
     * @description <p> 检测登录状态 </p>
     * @date 2023/4/25 1:03
     * @author MoZhu
     * @param token 用户token信息
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("检测登录状态")
    @GetMapping("/detect")
    public LoginVO detectLoginState(@RequestParam String token) {
        User user = tokenUtil.getUser(new Token(token));
        if (user == null) {
            throw new ApiException(ResultCode.LOGIN_FAIL);
        }
        return userInfoService.getUserByUserId(user.getId());
    }

    /**
     * @title addUser
     * @description <p> 注册用户 </p>
     * @date 2023/3/13 23:18
     * @author MoZhu
     * @param registerDTO 注册用户信息
     * @return {@link String}
     */
    @ApiOperation("注册用户")
    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterDTO registerDTO) throws ApiException {
        // 校验验证码
        String originVerifyCode = redisUtil.getOpsForValue().get(registerDTO.getUuid());
        if (originVerifyCode == null || (!originVerifyCode.equalsIgnoreCase(registerDTO.getVerifyCode()))) {
            throw new ApiException(ErrorCode.REGISTER_ERROR, "验证码错误");
        }
        switch(this.service.addUser(registerDTO)) {
            case 1:
                // 清除redis中的验证码
                redisUtil.getTemplate().delete(registerDTO.getUuid());
                return "注册成功";
            case -1:
                throw new ApiException(ErrorCode.REGISTER_ERROR, "用户名已存在");
            default:
                throw new ApiException(ErrorCode.REGISTER_ERROR, "注册失败");
        }
    }

    /**
     * @title removeUser
     * @description <p> 逻辑删除用户信息 </p>
     * @date 2023/3/15 4:13
     * @author MoZhu
     * @param ids id列表
     * @return {@link String}
     */
    @ApiOperation("注销用户")
    @DeleteMapping("/remove")
    public String removeUser(@RequestParam("userId") Ids ids) {
        if (this.service.remove(ids) > 0) {
            return "删除成功";
        } else {
            throw new ApiException(ResultCode.REMOVE_FAIL, "id不能为空");
        }
    }

    /**
     * @title modifyPassword
     * @description <p> 修改当前用户密码 </p>
     * @date 2023/3/14 2:26
     * @author MoZhu
     * @param changePasswordDTO 修改密码对象
     * @param request 请求报文
     * @return {@link String}
     */

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

    @ApiOperation("修改用户信息")
    @PutMapping("/modify")
    public ApiResult<?> modifyUser(@RequestBody @Valid ModifyUserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        if (this.service.modify(user) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success("修改成功");
    }

    /**
     * @title sendVerifyCode
     * @description <p> 发送邮箱验证码 </p>
     * @date 2023/4/28 1:20
     * @author MoZhu
     * @param targetMail 目标邮箱
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("发送验证码")
    @GetMapping("/sendVerifyCode")
    public String sendVerifyCode(@RequestParam("targetMail") String targetMail) {
        String verifyCode = StringUtil.generateVerifyCode();
        MailUtil.sendMail(targetMail,
                null,
                null,
                "OES在线考试平台注册验证",
                "欢迎使用OES在线考试平台<br>您的验证码为：<span style='color: skyblue; font-size: 25px'><b>" + verifyCode + "</b></span><br>验证码邮箱10分钟内有效，请尽快使用",
                null,
                null);
        // 将验证码存入redis中
        String uuid = UUID.randomUUID().toString().replace("-", "");
        redisUtil.getOpsForValue().set(uuid, verifyCode);
        redisUtil.setTime(uuid, 600L);
        return uuid;
    }
}
