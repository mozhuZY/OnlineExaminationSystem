package com.zy.oes.module.user.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.Permission;
import com.zy.oes.module.user.entity.dto.ModifyPermissionDTO;
import com.zy.oes.module.user.entity.dto.PermissionDTO;
import com.zy.oes.module.user.entity.dto.UpdateRolePermissionDTO;
import com.zy.oes.module.user.entity.vo.PermissionVO;
import com.zy.oes.module.user.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "权限接口")
@RestController
@RequestMapping("/api/user/permission")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    /**
     * @title getPermissionByRoleId
     * @description <p> 根据角色id查询角色所有权限 </p>
     * @date 2023/3/17 0:58
     * @author MoZhu
     * @param roleId 角色id
     * @return {@link List<PermissionVO>}
     */
    @ApiOperation("查询角色所有权限")
    @GetMapping("/get/byRoleId")
    public List<PermissionVO> getPermissionByRoleId(@RequestParam("roleId") Long roleId) {
        List<PermissionVO> list = this.service.getPermissionsByRoleId(roleId);
        if (list == null || list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "该角色没有被分配权限");
        }
        return list;
    }

    /**
     * @title getPermissionByUserId
     * @description <p> 根据用户id查询用户所有权限 </p>
     * @date 2023/3/17 0:59
     * @author MoZhu
     * @param userId 用户id
     * @return {@link List<PermissionVO>}
     */
    @ApiOperation("查询用户所有权限")
    @GetMapping("/get/byUserId")
    public List<PermissionVO> getPermissionByUserId(@RequestParam("userId") Long userId) {
        List<PermissionVO> list = this.service.getPermissionsByUserId(userId);
        if (list == null || list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "该用户没有任何权限");
        }
        return list;
    }

    /**
     * @title getPage
     * @description <p> 分页查询权限信息 </p>
     * @date 2023/3/16 1:43
     * @author MoZhu
     * @param pageDTO 分页信息DTO
     * @return {@link PageInfo<Permission>}
     */
    @ApiOperation("分页查询权限")
    @GetMapping("/page/get")
    public PageInfo<Permission> getPage(PageDTO pageDTO) {
        PageInfo<Permission> page = this.service.getPage(pageDTO);
        if (page == null || page.getSize() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        return page;
    }

    @ApiOperation("新增权限")
    @PostMapping("/add")
    public String addPermission(@RequestBody @Valid PermissionDTO dto) throws ApiException {
        Permission permission = new Permission();
        BeanUtils.copyProperties(dto, permission);
        if (this.service.add(permission) == 1) {
            return "添加成功";
        } else {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
    }

    /**
     * @title addUserRoles
     * @description <p> 批量新增角色权限关系 </p>
     * @date 2023/3/17 2:14
     * @author MoZhu
     * @param dto 批量跟新角色用户关系DTO
     * @return {@link java.lang.String}
     */
    @ApiOperation("新增角色权限关系")
    @PostMapping("/add/rolePermission")
    public String addUserRoles(@RequestBody @Valid UpdateRolePermissionDTO dto) {
        if (this.service.addRolePermissions(dto) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return "添加成功";
    }

    /**
     * @title removeUserRoles
     * @description <p> 批量删除角色权限关系 </p>
     * @date 2023/3/17 2:14
     * @author MoZhu
     * @param dto 批量跟新角色用户关系DTO
     * @return {@link java.lang.String}
     */
    @ApiOperation("删除角色权限关系")
    @DeleteMapping("/remove/rolePermission")
    public String removeUserRoles(@RequestBody @Valid UpdateRolePermissionDTO dto) {
        if (this.service.removeRolePermissions(dto) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return "删除成功";
    }

    /**
     * @title remove
     * @description <p> 删除权限 </p>
     * @date 2023/3/16 1:25
     * @author MoZhu
     * @param ids 待删除权限的id列表
     * @return {@link String}
     */
    @ApiOperation("删除权限")
    @DeleteMapping("/remove")
    public String removePermission(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) > 0) {
            return "删除成功";
        } else {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
    }

    /**
     * @title modifyPermission
     * @description <p> 修改权限信息 </p>
     * @date 2023/3/16 1:34
     * @author MoZhu
     * @param dto 修改权限信息DTO
     * @return {@link String}
     */
    @ApiOperation("修改权限信息")
    @PutMapping("/modify")
    public String modifyPermission(@RequestBody @Valid ModifyPermissionDTO dto) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(dto, permission);
        if (this.service.modify(permission) == 1) {
            return "修改成功";
        } else {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
    }
}
