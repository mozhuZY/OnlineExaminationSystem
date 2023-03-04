package com.zy.oes.module.user.service.impl;

import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.mapper.RoleMapper;
import com.zy.oes.module.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
