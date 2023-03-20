package com.zy.oes.module.question.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.question.entity.BlankQue;
import com.zy.oes.module.question.entity.vo.BlankQueVO;

import java.util.List;

/**
 * <p>
 * 填空题表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IBlankQueService extends IBaseService<BlankQue> {

    /**
     * @title getBlankQuestionPage
     * @description <p> 分月查询填空题 </p>
     * @date 2023/3/20 14:41
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<BlankQueVO>}
     */
    PageInfo<BlankQueVO> getBlankQuestionPage(PageDTO pageDTO);

    /**
     * @title getBlankQuestionListByPaperId
     * @description <p> 根据试卷id获取试题VO </p>
     * @date 2023/3/20 21:21
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<BlankQueVO>}
     */
    List<BlankQueVO> getBlankQuestionListByPaperId(Long paperId);
}
