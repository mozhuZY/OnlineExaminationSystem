package com.zy.oes.module.user.mapper;

import com.zy.oes.module.user.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.user.entity.dto.PermissionDTO;
import com.zy.oes.module.user.entity.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * @title selectPermissionByRoleId
     * @description <p> 根据角色id查询角色所有权限 </p>
     * @date 2023/3/17 0:27
     * @author MoZhu
     * @param roleId 角色id
     * @return {@link List<PermissionVO>}
     */
    List<PermissionVO> selectPermissionByRoleId(@Param("roleId") Long roleId);

    /**
     * @title selectPermissionByUserId
     * @description <p> 根据用户id查询用户所有权限 </p>
     * @date 2023/3/17 0:50
     * @author MoZhu
     * @param userId 用户id
     * @return {@link List<PermissionVO>}
     */
    List<PermissionVO> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * @title insertRolePermission
     * @description <p> 批量新增角色权限关系 </p>
     * @date 2023/3/17 2:05
     * @author MoZhu
     * @param roleId 角色id
     * @param permissionIds 权限id列表
     * @return {@link int}
     */
    int insertRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    /**
     * @title deleteRolePermission
     * @description <p> 批量删除角色权限关系 </p>
     * @date 2023/3/17 2:06
     * @author MoZhu
     * @param roleId 角色id
     * @param permissionIds 权限id列表
     * @return {@link int}
     */
    int deleteRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);
}
