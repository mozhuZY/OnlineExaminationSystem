package com.zy.oes.module.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.question.entity.JudgeQue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 判断题表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface JudgeQueMapper extends BaseMapper<JudgeQue> {
    /**
     * @title selectJudgeQue
     * @description <p> 根据试卷id查询判断题（PaperMapper专用） </p>
     * @date 2023/3/19 20:30
     * @author MoZhu
     * @return {@link JudgeQue}
     */
    List<JudgeQue> selectJudgeQueList();

    /**
     * @title selectJudgeQueListByPaperId
     * @description <p> 根据试卷id获取判断题 </p>
     * @date 2023/3/20 21:27
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<JudgeQue>}
     */
    List<JudgeQue> selectJudgeQueListByPaperId(@Param("paperId") Long paperId);
}
