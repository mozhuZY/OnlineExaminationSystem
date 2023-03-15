package com.zy.oes.module.user.service;

import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.user.entity.vo.UserInfoVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-15
 */
public interface IUserInfoService extends IBaseService<UserInfo> {

    /**
     * @title getUserByUserId
     * @description <p> 根据用户id查询用户信息 </p>
     * @date 2023/3/14 1:32
     * @author MoZhu
     * @param userId 用户id
     * @return {@link com.zy.oes.module.user.entity.User}
     */
    UserInfoVO getUserByUserId(Long userId);

    /**
     * @title modifyUserInfo
     * @description <p> 修改用户信息 </p>
     * @date 2023/3/15 3:25
     * @author MoZhu
     * @param userInfo 用户信息
     * @return {@link int}
     */
    int modifyUserInfo(UserInfo userInfo);
}
