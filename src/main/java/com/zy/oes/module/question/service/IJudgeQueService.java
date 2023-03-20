package com.zy.oes.module.question.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.question.entity.JudgeQue;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;

import java.util.List;

/**
 * <p>
 * 判断题表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IJudgeQueService extends IBaseService<JudgeQue> {

    /**
     * @title getJudgeQuestionPage
     * @description <p> 分页查询判断题信息 </p>
     * @date 2023/3/20 14:49
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<JudgeQueVO>}
     */
    PageInfo<JudgeQueVO> getJudgeQuestionPage(PageDTO pageDTO);

    /**
     * @title getJudgeQuestionListByPaperId
     * @description <p> 根据试卷id获取判断题 </p>
     * @date 2023/3/20 21:26
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<JudgeQueVO>}
     */
    List<JudgeQueVO> getJudgeQuestionListByPaperId(Long paperId);
}
