package com.zy.oes.module.exam.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.exam.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.exam.entity.dto.GetExamPageDTO;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import com.zy.oes.module.exam.entity.vo.StudentExamVO;
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
    OesPage<ExamVO> getExamPage(GetExamPageDTO pageDTO);

    /**
     * @title getEnrollExam
     * @description <p> 获取正在报名的考试信息 </p>
     * @date 2023/5/1 19:34
     * @author MoZhu
     * @return {@link List<ExamVO>}
     */
    @Transactional
    List<ExamVO> getEnrollExam();

    /**
     * @title getCurrentUserPage
     * @description <p> 分页查询教师考试信息 </p>
     * @date 2023/5/2 1:16
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ExamVO>}
     */
    @Transactional
    OesPage<ExamVO> getCurrentTeacherPage(PageDTO pageDTO);

    /**
     * @title checkExamDate
     * @description <p> 检查考试日期 </p>
     * @date 2023/5/4 3:38
     * @author MoZhu
     * @param exam 考试
     * @return {@link null}
     */
    void checkExamDate(Exam exam);
}
