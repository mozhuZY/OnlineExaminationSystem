package com.zy.oes.module.user.service.impl;

import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.Permission;
import com.zy.oes.module.user.entity.dto.UpdateRolePermissionDTO;
import com.zy.oes.module.user.entity.vo.PermissionVO;
import com.zy.oes.module.user.mapper.PermissionMapper;
import com.zy.oes.module.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionVO> getPermissionsByRoleId(Long roleId) {
        return this.baseMapper.selectPermissionByRoleId(roleId);
    }

    @Override
    public List<PermissionVO> getPermissionsByUserId(Long userId) {
        return this.baseMapper.selectPermissionByUserId(userId);
    }

    @Override
    public int addRolePermissions(UpdateRolePermissionDTO dto) {
        if (dto.getPermissionId() == null || dto.getPermissionId().size() == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "请至少新增一个权限");
        }
        return this.baseMapper.insertRolePermission(dto.getRoleId(), dto.getPermissionId());
    }

    @Override
    public int removeRolePermissions(UpdateRolePermissionDTO dto) {
        if (dto.getPermissionId() == null || dto.getPermissionId().size() == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "请至少删除一个权限");
        }
        return this.baseMapper.insertRolePermission(dto.getRoleId(), dto.getPermissionId());
    }
}
