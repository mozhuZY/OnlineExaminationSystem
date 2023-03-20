package com.zy.oes.module.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.question.entity.ChoiceQue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 选择题表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface ChoiceQueMapper extends BaseMapper<ChoiceQue> {
    /**
     * @title selectChoiceQue
     * @description <p> 根据试卷id查询选择题（PaperMapper专用） </p>
     * @date 2023/3/19 20:30
     * @author MoZhu
     * @return {@link ChoiceQue}
     */
    List<ChoiceQue> selectChoiceQueList();

    /**
     * @title selectChoiceQueListByPaperId
     * @description <p> 根据试卷id查询选择题 </p>
     * @date 2023/3/20 21:09
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<ChoiceQue>}
     */
    List<ChoiceQue> selectChoiceQueListByPaperId(@Param("paperId") Long paperId);
}
