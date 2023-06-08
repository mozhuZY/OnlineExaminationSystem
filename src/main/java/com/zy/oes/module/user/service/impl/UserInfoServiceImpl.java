package com.zy.oes.module.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.EntityStateUtil;
import com.zy.oes.module.file.entity.Picture;
import com.zy.oes.module.file.service.IPictureService;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.user.entity.Role;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.entity.dto.GetUserInfoVOPageDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.entity.vo.RoleVO;
import com.zy.oes.module.user.entity.vo.UserInfoVO;
import com.zy.oes.module.user.mapper.UserInfoMapper;
import com.zy.oes.module.user.mapper.UserMapper;
import com.zy.oes.module.user.service.IRoleService;
import com.zy.oes.module.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-15
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IRoleService roleService;

    @Override
    public LoginVO getUserByUserId(Long userId) {
        LoginVO vo = new LoginVO();
        User user = this.userMapper.selectOne(new QueryWrapper<User>()
                .eq("id", userId)
                .eq("state", 1));
        if (user == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "用户不存在");
        }
        BeanUtils.copyProperties(user, vo);
        // 获取用户信息
        UserInfo info = this.baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_id", user.getId()));
        BeanUtils.copyProperties(info, vo);
        // 获取头像
        Picture picture = pictureService.getOne(new QueryWrapper<Picture>().eq("id", info.getPictureId()).eq("state", 1));
        if (picture != null) {
            vo.setPicture(picture.getName());
        }
        // 获取角色信息
        List<RoleVO> roles = roleService.getRolesByUserId(user.getId());
        vo.setRole(roles.get(0).getId());
        return vo;
    }

    @Override
    public int modifyUserInfo(UserInfo userInfo) {
        // 获取当前用户id
        Long userId = this.tokenUtil.getCurrentUser().getId();
        // 设置修改时间
        userInfo.setUpdateTime(new Date());

        // 执行修改
        return this.baseMapper.update(userInfo, new UpdateWrapper<UserInfo>().eq("user_id", userId));
    }

    @Override
    public OesPage<UserInfoVO> getUserInfoVOPage(GetUserInfoVOPageDTO pageDTO) {
        if (pageDTO.getPageNum() < 1 || pageDTO.getPageSize() < 1) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }

        // 设置分页信息
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());

        // 查询用户信息
        List<User> list = userMapper.selectList(new QueryWrapper<User>()
                .eq(pageDTO.getState() != null, "state", pageDTO.getState())
        );
        OesPage<UserInfoVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(toVO(list));
        return page;
    }

    // 实体转换VO
    private List<UserInfoVO> toVO(List<User> list) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<UserInfoVO> result = new ArrayList<>();
        for (User user : list) {
//            UserInfoVO vo = new UserInfoVO();
            if (roleService.getRolesByUserId(user.getId()).get(0).getId() != 2) {
                continue;
            }

            UserInfo userInfo = this.getOne(new QueryWrapper<UserInfo>().eq("user_id", user.getId()));

            result.add(new UserInfoVO(user, userInfo));
        }
        return result;
    }
}
