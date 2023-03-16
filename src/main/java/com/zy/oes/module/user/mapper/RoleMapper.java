package com.zy.oes.module.user.mapper;

import com.zy.oes.module.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.user.entity.dto.RoleDTO;
import com.zy.oes.module.user.entity.vo.RoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @title selectRoleByUserId
     * @description <p> 根据用户id查询用户所有角色 </p>
     * @date 2023/3/17 0:31
     * @author MoZhu
     * @param userId 用户id
     * @return {@link List<RoleDTO>}
     */
    List<RoleVO> selectRoleByUserId(@Param("userId") Long userId);

    /**
     * @title insertUserRole
     * @description <p> 批量新增用户角色关系 </p>
     * @date 2023/3/17 1:13
     * @author MoZhu
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return {@link int}
     */
    int insertUserRole(@Param("userId") Long userId, @Param("roleId") List<Long> roleIds);

    /**
     * @title deleteUserRole
     * @description <p> 批量删除用户角色关系 </p>
     * @date 2023/3/17 1:31
     * @author MoZhu
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return {@link int}
     */
    int deleteUserRole(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);
}
