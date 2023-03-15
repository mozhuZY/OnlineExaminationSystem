package com.zy.oes.module.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.token.Token;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.entity.vo.UserInfoVO;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IUserInfoService;
import com.zy.oes.module.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        // 查询用户详细信息
        UserInfo info = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", user.getId()));
        LoginVO vo = new LoginVO();
        BeanUtils.copyProperties(user, vo);
        BeanUtils.copyProperties(info, vo);
        vo.setToken(tokenUtil.setToken(user).getToken());
        LOGGER.info(user.getUsername() + " login");
        return vo;
    }

    @Override
    public int addUser(User user) {
        // 用户名不能重复
        if (this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
            return -1;
        }
        // 过滤并设置新增用户的字段
        user.setId(null);
        // 用户密码加密
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        // 设置创建日期
        user.setCreateTime(new Date());
        // 设置更新日期
        user.setUpdateTime(user.getCreateTime());
        // 默认删除标识未false
        user.setIsDel(false);

        //新增记录
        if (this.baseMapper.insert(user) == 0) {
            return -2;
        }

        //新增用户对应的用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setCreateTime(user.getCreateTime());
        userInfo.setUpdateTime(user.getUpdateTime());
        userInfo.setIsDel(false);
        if (userInfoService.save(userInfo)) {
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
