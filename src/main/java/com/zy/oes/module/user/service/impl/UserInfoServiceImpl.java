package com.zy.oes.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.vo.UserInfoVO;
import com.zy.oes.module.user.mapper.UserInfoMapper;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-15
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfoVO getUserByUserId(Long userId) {
        User user = this.userMapper.selectById(userId);
        if (user == null) {
            return null;
        }
        UserInfo info = this.baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_id", user.getId()));
        return new UserInfoVO(user, info);
    }

    @Override
    public int modifyUserInfo(UserInfo userInfo) {
        // 获取当前用户id
        Long userId = this.tokenUtil.getCurrentUser().getId();
        // 设置修改时间
        userInfo.setUpdateTime(new Date());

        // 执行修改
        return this.baseMapper.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_id", userId));
    }
}
