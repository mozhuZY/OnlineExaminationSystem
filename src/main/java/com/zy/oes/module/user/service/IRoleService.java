package com.zy.oes.module.user.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.user.entity.Role;

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
}
