package com.zy.oes.module.answer.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.module.answer.entity.Answer;
import com.zy.oes.module.answer.entity.dto.AnswerDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerDetailDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerPageDTO;
import com.zy.oes.module.answer.entity.dto.ModifyAnswerDTO;
import com.zy.oes.module.answer.entity.vo.AnswerDetailVO;
import com.zy.oes.module.answer.entity.vo.AnswerVO;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@SuppressWarnings("javadoc")
public interface IAnswerService extends IBaseService<Answer> {

    /**
     * @title getAnswer
     * @description <p> 根据考生id获取试卷 </p>
     * @date 2023/3/21 19:27
     * @author MoZhu
     * @param dto
     * @return {@link PageInfo<AnswerVO>}
     */
    PageInfo<AnswerVO> getAnswerPage(GetAnswerPageDTO dto);

    /**
     * @title getAnswerDetail
     * @description <p> 根据考生id获取试卷详细信息 </p>
     * @date 2023/3/22 2:18
     * @author MoZhu
     * @param dto 获取试卷详细信息DTO
     * @return {@link AnswerDetailVO}
     */
    AnswerDetailVO getAnswerDetail(GetAnswerDetailDTO dto);

    /**
     * @title addAnswer
     * @description <p> 新增答卷 </p>
     * @date 2023/3/21 17:32
     * @author MoZhu
     * @param dto 新增答卷DTO
     * @return {@link ApiResult<?>}
     */
    @Transactional
    ApiResult<?> addAnswer(AnswerDTO dto);

    /**
     * @title modifyAnswer
     * @description <p> 修改答卷分数 </p>
     * @date 2023/3/24 0:29
     * @author MoZhu
     * @param dto 修改分数列表
     * @return {@link ApiResult<?>}
     */
    @Transactional
    ApiResult<?> modifyAnswer(ModifyAnswerDTO dto);
}
