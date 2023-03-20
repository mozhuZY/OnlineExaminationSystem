package com.zy.oes.module.question.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.question.entity.SubQue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.oes.module.question.entity.vo.SubQueVO;

import java.util.List;

/**
 * <p>
 * 主观题表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface ISubQueService extends IBaseService<SubQue> {

    /**
     * @title getSubQuestionPage
     * @description <p> 分页查询主观题信息 </p>
     * @date 2023/3/20 14:56
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<SubQueVO>}
     */
    PageInfo<SubQueVO> getSubQuestionPage(PageDTO pageDTO);

    /**
     * @title getSubQuestionListByPaperId
     * @description <p> 根据试卷id获取主观题 </p>
     * @date 2023/3/20 21:31
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<SubQueVO>}
     */
    List<SubQueVO> getSubQuestionListByPaperId(Long paperId);
}
