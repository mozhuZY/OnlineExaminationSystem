package com.zy.oes.module.paper.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.dto.AddPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.ModifyPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.RemovePaperQuestionDTO;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.entity.vo.PaperInfoVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 试卷表 服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface IPaperService extends IBaseService<Paper> {
    /**
     * @title getPaperDetail
     * @description <p> 根据id查询试卷详细信息 </p>
     * @date 2023/3/19 14:15
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link PaperDetailVO}
     */
    PaperDetailVO getPaperDetail(Long paperId);

    /**
     * @title getPaperPage
     * @description <p> 查询试卷分页信息 </p>
     * @date 2023/3/19 19:32
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<PaperInfoVO>}
     */
    PageInfo<PaperInfoVO> getPaperPage(PageDTO pageDTO);

    /**
     * @title getPaperPage
     * @description <p> 查询当前用户试卷分页信息 </p>
     * @date 2023/3/19 19:32
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<PaperInfoVO>}
     */
    OesPage<PaperInfoVO> getCurrentUserPaperPage(PageDTO pageDTO);

    /**
     * @title insertQuestion
     * @description <p> 新增试卷试题关系 </p>
     * @date 2023/3/19 15:44
     * @author MoZhu
     * @param dto 新增试卷试题DTO
     * @return {@link ApiResult<Object>}
     */
    @Transactional
    ApiResult<Object> addPaperQuestion(AddPaperQuestionDTO dto);

    /**
     * @title deleteQuestion
     * @description <p> 删除试卷试题关系 </p>
     * @date 2023/3/19 15:44
     * @author MoZhu
     * @param dto 删除试卷试题DTO
     * @return {@link ApiResult<Object>}
     */
    @Transactional
    ApiResult<Object> removePaperQuestion(RemovePaperQuestionDTO dto);

    /**
     * @title modifyQuestion
     * @description <p> 修改试卷试题关系 </p>
     * @date 2023/3/19 15:45
     * @author MoZhu
     * @param dto 修改试卷试题DTO
     * @return {@link ApiResult<Object>}
     */
    @Transactional
    ApiResult<Object> modifyPaperQuestion(ModifyPaperQuestionDTO dto);
}
