package com.zy.oes.module.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.entity.dto.RoleDTO;
import com.zy.oes.module.user.entity.dto.ModifyRoleDTO;
import com.zy.oes.module.user.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Date;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("/api/user/role")
public class RoleController {

    @Autowired
    private IRoleService service;

    /**
     * @title getPage
     * @description <p> 获取分页角色信息 </p>
     * @date 2023/3/15 22:53
     * @author MoZhu
     * @param pageDTO 分页信息DTO
     * @return {@link PageInfo<Role>}
     */
    @ApiOperation("获取分页角色信息")
    @GetMapping("/page/get")
    public PageInfo<Role> getPage(PageDTO pageDTO) {
        PageInfo<Role> page = this.service.getPage(pageDTO);
        if (page == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        return page;
    }

    /**
     * @title addRole
     * @description <p> 新增角色 </p>
     * @date 2023/3/15 16:42
     * @author MoZhu
     * @param dto 新增角色DTO
     * @return {@link String}
     */
    @ApiOperation("新增角色")
    @PostMapping("/add")
    public String addRole(@RequestBody @Valid RoleDTO dto) throws ApiException {
        Role role = new Role();
        // 角色名称不能重复
        if (this.service.getOne(new QueryWrapper<Role>().eq("role_name", dto.getRoleName())) != null) {
            throw new ApiException(ResultCode.ADD_FAIL, "角色名称重复");
        }
        // 若添加上级角色，上级角色id必须存在
        if (dto.getPre() != null && this.service.getById(dto.getPre()) == null) {
            throw new ApiException(ResultCode.ADD_FAIL, "上级角色不存在");
        }
        BeanUtils.copyProperties(dto, role);
        role.setCreateTime(new Date());
        role.setUpdateTime(role.getCreateTime());
        role.setIsDel(false);
        if (this.service.add(role) == 1) {
            return "添加成功";
        } else {
            throw new ApiException(ResultCode.ADD_FAIL, "添加失败");
        }
    }

    /**
     * @title removeRole
     * @description <p> 删除角色 </p>
     * @date 2023/3/15 16:46
     * @author MoZhu
     * @param ids 待删除角色id列表
     * @return {@link String}
     */
    @ApiOperation("删除角色")
    @DeleteMapping("/remove")
    public String removeRole(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) > 0) {
            return "删除成功";
        } else {
            throw new ApiException(ResultCode.REMOVE_FAIL, "删除失败");
        }
    }

    /**
     * @title modifyRole
     * @description <p> 修改角色信息 </p>
     * @date 2023/3/15 17:24
     * @author MoZhu
     * @param dto 修改角色信息DTO
     * @return {@link String}
     */
    @ApiOperation("修改角色信息")
    @PutMapping("/modify")
    public String modifyRole(@RequestBody @Valid ModifyRoleDTO dto) {
        // 验证修改的i角色是否存在
        if (this.service.getById(dto.getId()) == null) {
            throw new ApiException(ResultCode.MODIFY_FAIL, "此角色不存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(dto, role);
        if (this.service.modify(role) == 1) {
            return "修改成功";
        } else {
            throw new ApiException(ResultCode.MODIFY_FAIL, "修改失败");
        }
    }
}
