package com.zy.oes.module.user.controller;


import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.dto.ModifyUserInfoDTO;
import com.zy.oes.module.user.entity.vo.UserInfoVO;
import com.zy.oes.module.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-15
 */
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

    @Autowired
    private IUserInfoService service;

    /**
     * @title modifyUserInfo
     * @description <p> 修改用户信息 </p>
     * @date 2023/3/15 4:03
     * @author MoZhu
     * @param dto 修改用户信息DTO
     * @return {@link String}
     */
    @ApiOperation("修改用户信息")
    @PutMapping("/modify")
    public String modifyUserInfo(@RequestBody @Valid ModifyUserInfoDTO dto) throws ApiException {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(dto, userInfo);
        if (this.service.modifyUserInfo(userInfo) == 0) {
            throw new ApiException(ResultCode.UPDATE_FAIL, "此用户不存在");
        }
        return "修改成功";
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
    public UserInfoVO getUserByUserId(@RequestParam("userId") Long userId) throws ApiException {
        UserInfoVO vo = this.service.getUserByUserId(userId);
        if (vo == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "查询用户不存在");
        }
        return vo;
    }
}
