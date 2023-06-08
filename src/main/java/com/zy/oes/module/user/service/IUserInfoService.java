package com.zy.oes.module.user.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.user.entity.dto.GetUserInfoVOPageDTO;
import com.zy.oes.module.user.entity.vo.LoginVO;
import com.zy.oes.module.user.entity.vo.UserInfoVO;
import org.springframework.transaction.annotation.Transactional;

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
     * @return {@link com.zy.oes.module.user.entity.vo.LoginVO}
     */
    LoginVO getUserByUserId(Long userId);

    /**
     * @title modifyUserInfo
     * @description <p> 修改用户信息 </p>
     * @date 2023/3/15 3:25
     * @author MoZhu
     * @param userInfo 用户信息
     * @return {@link int}
     */
    @Transactional
    int modifyUserInfo(UserInfo userInfo);

    /**
     * @title getUserInfoVOPage
     * @description <p> 条件查询用户 </p>
     * @date 2023/5/5 18:58
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<UserInfoVO>}
     */
    OesPage<UserInfoVO> getUserInfoVOPage(GetUserInfoVOPageDTO pageDTO);
}
