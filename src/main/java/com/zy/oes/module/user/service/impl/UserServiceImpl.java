package com.zy.oes.module.user.service.impl;

import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
