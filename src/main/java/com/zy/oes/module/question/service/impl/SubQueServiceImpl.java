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
import com.zy.oes.module.paper.entity.RPaperSq;
import com.zy.oes.module.paper.service.IRPaperSqService;
import com.zy.oes.module.question.entity.SubQue;
import com.zy.oes.module.question.entity.vo.SubQueVO;
import com.zy.oes.module.question.mapper.SubQueMapper;
import com.zy.oes.module.question.service.ISubQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 主观题表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class SubQueServiceImpl extends BaseServiceImpl<SubQueMapper, SubQue> implements ISubQueService {

    @Autowired
    private IRPaperSqService sqService;

    @Override
    public PageInfo<SubQueVO> getSubQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<SubQue> list = this.baseMapper.selectSubQueList();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 转换为VO
        return new PageInfo<>(entityToVO(list, null));
    }

    @Override
    public List<SubQueVO> getSubQuestionListByPaperId(Long paperId) {
        List<SubQue> list = this.baseMapper.selectSubQueListByPaperId(paperId);
        if (list.size() == 0) {
            return null;
        }
        // 获取试题分数
        List<RPaperSq> sqs = sqService.getBaseMapper().selectList(new QueryWrapper<RPaperSq>()
                .eq("p_id", paperId)
                .in("q_id", list.stream().map(SubQue::getId).collect(Collectors.toList())));
        // 先排序
        list.sort((o1, o2) -> o1.getId() < o2.getId() ? 0 : 1);
        sqs.sort((o1, o2) -> o1.getQId() < o2.getQId() ? 0 : 1);
        //转换实体类
        return entityToVO(list, sqs);
    }

    /**
     * @title entityToVO
     * @description <p> 实体转换为VO </p>
     * @date 2023/3/20 21:34
     * @author MoZhu
     * @param list 实体类对象列表
     * @return {@link List<SubQueVO>}
     */
    private List<SubQueVO> entityToVO(List<SubQue> list, List<RPaperSq> sqs) {
        List<SubQueVO> vos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SubQueVO vo = new SubQueVO();
            BeanUtils.copyProperties(list.get(i), vo);
            vo.setTags(StringUtil.combineStringToList(list.get(i).getTags()));
            if (sqs != null) {
                vo.setScore(sqs.get(i).getScore());
            }
            vos.add(vo);
        }
        return vos;
    }
}
