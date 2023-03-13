package com.zy.oes.module.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.token.Token;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Override
    public User login(LoginDTO loginDTO) {
        if (Objects.isNull(loginDTO)) {
            return null;
        }
        // 查询是否存在此账号
        User user = this.baseMapper.selectOne(new QueryWrapper<User>().eq("username", loginDTO.getUsername()));
        if (user == null || !DigestUtil.md5Hex(loginDTO.getPassword()).equals(user.getPassword())) {
            // 用户名或密码错误
            return null;
        }
        LOGGER.info(user.getUsername() + " login");
        return user;
    }

    @Override
    public User getUserByUserId(Long userId) {
        return this.baseMapper.selectById(userId);
    }

    @Override
    public int addUser(User user) {
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
        return this.baseMapper.insert(user);
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
