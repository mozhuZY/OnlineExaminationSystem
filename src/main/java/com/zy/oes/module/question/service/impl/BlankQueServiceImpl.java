package com.zy.oes.module.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.module.paper.entity.RPaperBq;
import com.zy.oes.module.paper.service.IRPaperBqService;
import com.zy.oes.module.question.entity.BlankQue;
import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.mapper.BlankQueMapper;
import com.zy.oes.module.question.service.IBlankQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 填空题表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class BlankQueServiceImpl extends BaseServiceImpl<BlankQueMapper, BlankQue> implements IBlankQueService {

    @Autowired
    private IRPaperBqService bqService;

    @Override
    public PageInfo<BlankQueVO> getBlankQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<BlankQue> list = this.baseMapper.selectBlankQueList();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 转换为VO
        return new PageInfo<>(entityToVO(list, null));
    }

    @Override
    public List<BlankQueVO> getBlankQuestionListByPaperId(Long paperId) {
        List<BlankQue> list = this.baseMapper.selectBlankQueListByPaperId(paperId);
        if (list.size() == 0) {
            return null;
        }
        // 获取试题分数
        List<RPaperBq> bqs = bqService.getBaseMapper().selectList(new QueryWrapper<RPaperBq>()
                .eq("p_id", paperId)
                .in("q_id", list.stream().map(BlankQue::getId).collect(Collectors.toList())));
        // 先排序
        list.sort((o1, o2) -> o1.getId() < o2.getId() ? 0 : 1);
        bqs.sort((o1, o2) -> o1.getQId() < o2.getQId() ? 0 : 1);
        //转换实体类
        return entityToVO(list, bqs);
    }

    /**
     * @title entityToVO
     * @description <p> 实体类转换为VO </p>
     * @date 2023/3/20 21:24
     * @author MoZhu
     * @param list 实体列表
     * @return {@link List<BlankQueVO>}
     */
    private List<BlankQueVO> entityToVO(List<BlankQue> list, List<RPaperBq> bqs) {
        List<BlankQueVO> vos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BlankQueVO vo = new BlankQueVO();
            BeanUtils.copyProperties(list.get(i), vo);
            vo.setTags(StringUtil.combineStringToList(list.get(i).getTags()));
            vo.setSpaces(StringUtil.combineStringToList(list.get(i).getSpaces()));
            if (bqs != null) {
                vo.setScore(bqs.get(i).getScore());
            }
            vos.add(vo);
        }
        return vos;
    }
}
