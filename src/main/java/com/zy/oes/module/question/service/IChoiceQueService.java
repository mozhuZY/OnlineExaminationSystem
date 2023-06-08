package com.zy.oes.module.question.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.question.entity.ChoiceQue;
import com.zy.oes.module.question.entity.dto.GetChoiceQuestionConditionDTO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;

import java.util.List;

/**
 * <p>
 * 选择题表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IChoiceQueService extends IBaseService<ChoiceQue> {

    /**
     * @title getChoiceQuestionPage
     * @description <p> 分页查询试题信息 </p>
     * @date 2023/3/20 14:31
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    OesPage<ChoiceQueVO> getChoiceQuestionPage(PageDTO pageDTO);

    /**
     * @title getCurrentUserChoiceQuestionPage
     * @description <p> 获取当前用户的选择题信息 </p>
     * @date 2023/5/2 17:16
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    OesPage<ChoiceQueVO> getCurrentUserChoiceQuestionPage(PageDTO pageDTO);

    /**
     * @title getCurrentUserChoiceQuestionPage
     * @description <p> 条件筛选获取选择题信息 </p>
     * @date 2023/5/2 17:16
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    OesPage<ChoiceQueVO> getChoiceQuestionPageByCondition(GetChoiceQuestionConditionDTO pageDTO);

    /**
     * @title getChoiceQuestionListByPageId
     * @description <p> 根据试卷id获取选择题 </p>
     * @date 2023/3/20 21:07
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<ChoiceQueVO>}
     */
    List<ChoiceQueVO> getChoiceQuestionListByPageId(Long paperId);
}
