package com.zy.oes.module.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.util.BeanExpandUtil;
import com.zy.oes.module.exam.entity.RExamExaminee;
import com.zy.oes.module.exam.entity.dto.EnrollPageDTO;
import com.zy.oes.module.exam.entity.vo.EnrollVO;
import com.zy.oes.module.exam.mapper.RExamExamineeMapper;
import com.zy.oes.module.exam.service.IRExamExamineeService;
import com.zy.oes.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-21
 */
@Service
public class RExamExamineeServiceImpl extends BaseServiceImpl<RExamExamineeMapper, RExamExaminee> implements IRExamExamineeService {

    @Autowired
    private IUserService userService;

    @Override
    public PageInfo<EnrollVO> getEnrollPage(EnrollPageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        if (userService.getById(pageDTO.getUserId()) == null) {
            throw new ApiException(ErrorCode.APP_ERROR, "用户信息不存在");
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<RExamExaminee> list = this.baseMapper.selectList(new QueryWrapper<RExamExaminee>().eq("user_id", pageDTO.getUserId()));

        return new PageInfo<>(BeanExpandUtil.toVOList(list, EnrollVO.class));
    }
}
