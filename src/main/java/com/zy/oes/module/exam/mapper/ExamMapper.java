package com.zy.oes.module.exam.mapper;

import com.zy.oes.module.exam.entity.Exam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.exam.entity.vo.ExamVO;

import java.util.List;

/**
 * <p>
 * 考试表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface ExamMapper extends BaseMapper<Exam> {
    List<ExamVO> selectPage();
}
