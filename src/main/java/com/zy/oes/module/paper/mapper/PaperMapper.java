package com.zy.oes.module.paper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.dto.AddPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.ModifyPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.RemovePaperQuestionDTO;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.entity.vo.PaperInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 试卷表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * @title getPaperDetail
     * @description <p> 根据id查询试卷详细信息 </p>
     * @date 2023/3/19 14:15
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link PaperDetailVO}
     */
    PaperDetailVO selectPaperDetail(@Param("paperId") Long paperId);

    /**
     * @title selectPaperPage
     * @description <p> 查询试卷分页信息 </p>
     * @date 2023/3/19 19:29
     * @author MoZhu
     * @return {@link List<PaperInfoVO>}
     */
    List<PaperInfoVO> selectPaperPage();

    /**
     * @title insertQuestion
     * @description <p> 新增试卷试题关系 </p>
     * @date 2023/3/19 15:44
     * @author MoZhu
     * @param dto 新增试卷试题DTO
     * @return {@link int}
     */
    int insertQuestion(@Param("dto") AddPaperQuestionDTO dto);

    /**
     * @title deleteQuestion
     * @description <p> 删除试卷试题关系 </p>
     * @date 2023/3/19 15:44
     * @author MoZhu
     * @param dto 删除试卷试题DTO
     * @return {@link int}
     */
    int deleteQuestion(@Param("dto") RemovePaperQuestionDTO dto);

    /**
     * @title modifyQuestion
     * @description <p> 修改试卷试题关系 </p>
     * @date 2023/3/19 15:45
     * @author MoZhu
     * @param dto 修改试卷试题DTO
     * @return {@link int}
     */
    int updateQuestion(@Param("dto") ModifyPaperQuestionDTO dto);
}
