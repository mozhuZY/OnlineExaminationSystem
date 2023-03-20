package com.zy.oes.module.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.oes.module.question.entity.BlankQue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 填空题表 Mapper 接口
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
public interface BlankQueMapper extends BaseMapper<BlankQue> {

    /**
     * @title selectBlankQue
     * @description <p> 根据试卷id查询填空题（PaperMapper专用） </p>
     * @date 2023/3/19 20:30
     * @author MoZhu
     * @return {@link BlankQue}
     */
    List<BlankQue> selectBlankQueList();

    /**
     * @title selectBlankQueListByPaperId
     * @description <p> 根据试卷id获取试题VO </p>
     * @date 2023/3/20 21:22
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link List<BlankQue>}
     */
    List<BlankQue> selectBlankQueListByPaperId(@Param("paperId") Long paperId);
}
