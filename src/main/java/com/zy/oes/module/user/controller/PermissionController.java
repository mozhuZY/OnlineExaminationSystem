package com.zy.oes.module.user.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.Permission;
import com.zy.oes.module.user.entity.dto.ModifyPermissionDTO;
import com.zy.oes.module.user.entity.dto.PermissionDTO;
import com.zy.oes.module.user.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;

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
