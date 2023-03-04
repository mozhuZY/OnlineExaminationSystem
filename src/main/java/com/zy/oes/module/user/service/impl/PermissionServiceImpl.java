package com.zy.oes.module.user.service.impl;

import com.zy.oes.module.user.entity.Permission;
import com.zy.oes.module.user.mapper.PermissionMapper;
import com.zy.oes.module.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
