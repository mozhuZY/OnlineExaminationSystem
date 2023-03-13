package com.zy.oes.module.user.service;

import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.token.Token;
import com.zy.oes.module.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.user.entity.dto.ChangePasswordDTO;
import com.zy.oes.module.user.entity.dto.LoginDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IUserService extends IBaseService<User> {

    /**
     * @title login
     * @description <p> 登录 </p>
     * @date 2023/3/13 23:47
     * @author MoZhu
     * @param loginDTO 登录信息
     * @return {@link int}
     */
    User login(LoginDTO loginDTO);

    /**
     * @title getUserByUserId
     * @description <p> 根据用户id查询用户信息 </p>
     * @date 2023/3/14 1:32
     * @author MoZhu
     * @param userId 用户id
     * @return {@link com.zy.oes.module.user.entity.User}
     */

    User getUserByUserId(Long userId);

    /**
     * @title addUser
     * @description <p> 注册用户 </p>
     * @date 2023/3/13 23:46
     * @author MoZhu
     * @param user 注册用户信息
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int addUser(User user);

    /**
     * @title changePassword
     * @description <p> 修改密码 </p>
     * @date 2023/3/14 0:54
     * @author MoZhu
     * @param changePasswordDTO 修改密码DTO
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int changePassword(ChangePasswordDTO changePasswordDTO, Token token);
}
