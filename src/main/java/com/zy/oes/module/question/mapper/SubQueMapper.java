package com.zy.oes.module.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.question.entity.SubQue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 主观题表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface SubQueMapper extends BaseMapper<SubQue> {
    /**
     * @title selectSubQue
     * @description <p> 根据试卷id查询主观题（PaperMapper专用） </p>
     * @date 2023/3/19 20:30
     * @author MoZhu
     * @return {@link SubQue}
     */
    List<SubQue> selectSubQueList();

    /**
     * @title selectSubQueListByPaperId
     * @description <p> 根据试卷id查询主观题 </p>
     * @date 2023/3/20 21:32
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<SubQue>}
     */
    List<SubQue> selectSubQueListByPaperId(@Param("paperId") Long paperId);
}
