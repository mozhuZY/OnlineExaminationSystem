package com.zy.oes.module.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import com.zy.oes.module.exam.mapper.ExamMapper;
import com.zy.oes.module.exam.service.IExamService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 考试表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class ExamServiceImpl extends BaseServiceImpl<ExamMapper, Exam> implements IExamService {

    @Override
    public ExamVO getExam(Long id) {
        Exam exam = this.baseMapper.selectOne(new QueryWrapper<Exam>()
                .eq("id", id)
                .eq("is_del", 0));
        if (exam == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "不存在此考试信息");
        }
        ExamVO examVO = new ExamVO();
        BeanUtils.copyProperties(exam, examVO);
        return examVO;
    }

    @Override
    public PageInfo<ExamVO> getExamPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ExamVO> list = this.baseMapper.selectPage();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        return new PageInfo<>(list);
    }
}
