package com.zy.oes.module.user.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.entity.dto.UpdateUserRolesDTO;
import com.zy.oes.module.user.entity.vo.RoleVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * @title getPage
     * @description <p> 获取分页角色信息 </p>
     * @date 2023/3/15 17:41
     * @author MoZhu
     * @param pageDTO 分页信息DTO
     * @return {@link PageInfo<Role>}
     */
    PageInfo<Role> getPage(PageDTO pageDTO);

    /**
     * @title getRolesByUserId
     * @description <p> 获取用户的所有角色 </p>
     * @date 2023/3/17 0:18
     * @author MoZhu
     * @param userId 用户id
     * @return {@link List<RoleVO>}
     */
    List<RoleVO> getRolesByUserId(Long userId);

    /**
     * @title addUserRoles
     * @description <p> 批量新增用户角色关系 </p>
     * @date 2023/3/17 1:41
     * @author MoZhu
     * @param dto 批量更新用户角色关系DTO
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int addUserRoles(UpdateUserRolesDTO dto) throws ApiException;

    /**
     * @title removeUserRoles
     * @description <p> 批量删除用户角色关系 </p>
     * @date 2023/3/17 1:41
     * @author MoZhu
     * @param dto 批量更新用户角色关系DTO
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int removeUserRoles(UpdateUserRolesDTO dto) throws ApiException;
}
