package com.zy.oes.module.exam.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.exam.entity.RExamExaminee;
import com.zy.oes.module.exam.entity.dto.EnrollPageDTO;
import com.zy.oes.module.exam.entity.vo.EnrollVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-21
 */
public interface IRExamExamineeService extends IBaseService<RExamExaminee> {
    /**
     * @title getEnrollPage
     * @description <p> 根据用户id分页获取报名信息 </p>
     * @date 2023/3/21 16:45
     * @author MoZhu
     * @param pageDTO 分页查询报名信息DTO
     * @return {@link PageInfo<EnrollVO>}
     */
    OesPage<EnrollVO> getEnrollPage(EnrollPageDTO pageDTO);
}
