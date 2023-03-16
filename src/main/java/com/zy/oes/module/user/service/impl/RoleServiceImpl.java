package com.zy.oes.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.entity.dto.UpdateUserRolesDTO;
import com.zy.oes.module.user.entity.vo.RoleVO;
import com.zy.oes.module.user.mapper.RoleMapper;
import com.zy.oes.module.user.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public PageInfo<Role> getPage(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Role> roleList = this.baseMapper.selectList(new QueryWrapper<Role>().eq("is_del", false));
        if (roleList == null || roleList.size() == 0) {
            return null;
        }
        return new PageInfo<>(roleList);
    }

    @Override
    public List<RoleVO> getRolesByUserId(Long userId) {
        return this.baseMapper.selectRoleByUserId(userId);
    }

    @Override
    public int addUserRoles(UpdateUserRolesDTO dto) throws ApiException {
        if (dto.getRoleIds() == null || dto.getRoleIds().size() == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "请至少新增一个角色");
        }
        return this.baseMapper.insertUserRole(dto.getUserId(), dto.getRoleIds());
    }

    @Override
    public int removeUserRoles(UpdateUserRolesDTO dto) throws ApiException {
        if (dto.getRoleIds() == null || dto.getRoleIds().size() == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "请至少删除一个角色");
        }
        return this.baseMapper.deleteUserRole(dto.getUserId(), dto.getRoleIds());
    }
}
