package com.zy.oes.module.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.token.Token;
import com.zy.oes.module.file.service.IPictureService;
import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.entity.dto.RegisterDTO;
import com.zy.oes.module.user.entity.dto.UpdateUserRolesDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.entity.vo.RoleVO;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IRoleService;
import com.zy.oes.module.user.service.IUserInfoService;
import com.zy.oes.module.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPictureService pictureService;

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        if (Objects.isNull(loginDTO)) {
            return null;
        }
        // 查询是否存在此账号
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", loginDTO.getUsername()));
        if (user == null || !DigestUtil.md5Hex(loginDTO.getPassword()).equals(user.getPassword())) {
            // 用户名或密码错误
            return null;
        }

        // 判断用户状态
        if (user.getState() == 0) {
            throw new ApiException(ResultCode.LOGIN_FAIL, "该账号正在审核");
        } else if (user.getState() == 2) {
            throw new ApiException(ResultCode.LOGIN_FAIL, "该账号已被封禁");
        }

        // 查询用户详细信息
        UserInfo info = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", user.getId()));
        LoginVO vo = new LoginVO();
        BeanUtils.copyProperties(user, vo);
        BeanUtils.copyProperties(info, vo);
        vo.setToken(tokenUtil.setToken(user).getToken());
        if (info.getPictureId() != null) {
            // 获取头像
            vo.setPicture(pictureService.getById(info.getPictureId()).getName());
        }
        // 获取角色信息
        List<RoleVO> roles = roleService.getRolesByUserId(user.getId());
        vo.setRole(roles.get(0).getId());
        LOGGER.info(user.getUsername() + " login");
        return vo;
    }

    @Override
    public int addUser(RegisterDTO registerDTO) {
        // 用户名不能重复
        if (this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", registerDTO.getUsername())) != null) {
            return -1;
        }
        User user = new User();
        BeanUtils.copyProperties(registerDTO, user);
        // 过滤并设置新增用户的字段
        user.setId(null);
        // 用户密码加密
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        // 设置创建日期
        user.setCreateTime(new Date());
        // 设置更新日期
        user.setUpdateTime(user.getCreateTime());
        // 默认删除标识：未删除
        user.setIsDel(false);

        // 学生注册跳过审核
        if (registerDTO.getRole() == 3) {
            user.setState(1);
        }

        //新增记录
        if (this.baseMapper.insert(user) == 0) {
            return -2;
        }

        // 新增用户对应的用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setRealName(registerDTO.getRealName());
        userInfo.setCreateTime(user.getCreateTime());
        userInfo.setUpdateTime(user.getUpdateTime());
        userInfo.setIsDel(false);
        if (userInfoService.save(userInfo)) {
            return 1;
        }

        // 新增用户角色关系
        UpdateUserRolesDTO updateUserRolesDTO = new UpdateUserRolesDTO();
        updateUserRolesDTO.setUserId(user.getId());
        ArrayList<Long> roleId = new ArrayList<>();
        switch (registerDTO.getRole()) {
            case 1:
                // 学生
                roleId.add(1L);
                break;
            case 2:
                // 教师
                roleId.add(2L);
                break;
        }
        updateUserRolesDTO.setRoleIds(roleId);
        if (roleService.addUserRoles(updateUserRolesDTO) == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public int removeUser(Ids ids) {
        // id不能为空
        if (ids.getIds() == null) {
            return -1;
        }

        // 逻辑删除用户表中的记录
        if (this.remove(ids) == 0) {
            return -2;
        }

        // 逻辑删除用户信息表中的记录
        if (this.userInfoService.remove(ids) > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int changePassword(ChangePasswordDTO changePasswordDTO, Token token) {
        // 获取当前用户信息
        User user = this.tokenUtil.getUser(token);
        // 旧密码必须正确
        if (!user.getPassword().equals(DigestUtil.md5Hex(changePasswordDTO.getOldPassword()))) {
            return -1;
        }
        // 新旧密码不能相同
        String newPassword = DigestUtil.md5Hex(changePasswordDTO.getNewPassword());
        if (user.getPassword().equals(newPassword)) {
            return -2;
        }
        // 更新密码
        User change = new User();
        change.setId(user.getId());
        change.setPassword(newPassword);
        change.setUpdateTime(new Date());
        return this.baseMapper.updateById(change);
    }
}
