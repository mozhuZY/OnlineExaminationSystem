package com.zy.oes.module.user.service;

import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.user.entity.Permission;
import com.zy.oes.module.user.entity.dto.UpdateRolePermissionDTO;
import com.zy.oes.module.user.entity.vo.PermissionVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IPermissionService extends IBaseService<Permission> {
    /**
     * @title getPermissionsByRoleId
     * @description <p> 根据角色id查询角色所有权限 </p>
     * @date 2023/3/17 0:52
     * @author MoZhu
     * @param roleId 角色id
     * @return {@link List<PermissionVO>}
     */
    List<PermissionVO> getPermissionsByRoleId(Long roleId);

    /**
     * @title getPermissionsByUserId
     * @description <p> 根据用户id查询用户所有权限 </p>
     * @date 2023/3/17 0:52
     * @author MoZhu
     * @param userId 用户id
     * @return {@link List<PermissionVO>}
     */
    List<PermissionVO> getPermissionsByUserId(Long userId);

    /**
     * @title addRolePermissions
     * @description <p> 批量新增角色权限关系 </p>
     * @date 2023/3/17 2:10
     * @author MoZhu
     * @param dto 批量更新角色权限关系DTO
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int addRolePermissions(UpdateRolePermissionDTO dto);

    /**
     * @title removeRolePermissions
     * @description <p> 批量删除角色泉下关系 </p>
     * @date 2023/3/17 2:10
     * @author MoZhu
     * @param dto 批量更新角色权限关系DTO
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int removeRolePermissions(UpdateRolePermissionDTO dto);
}
