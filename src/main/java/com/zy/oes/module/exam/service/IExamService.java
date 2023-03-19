package com.zy.oes.module.exam.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.exam.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 考试表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IExamService extends IBaseService<Exam> {

    /**
     * @title getExam
     * @description <p> 根据id获取考试信息 </p>
     * @date 2023/3/18 21:27
     * @author MoZhu
     * @param id 考试id
     * @return {@link ExamVO}
     */
    @Transactional
    ExamVO getExam(Long id);

    /**
     * @title getPage
     * @description <p> 分页获取考试信息 </p>
     * @date 2023/3/18 21:27
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link List<ExamVO>}
     */
    @Transactional
    PageInfo<ExamVO> getExamPage(PageDTO pageDTO);
}
