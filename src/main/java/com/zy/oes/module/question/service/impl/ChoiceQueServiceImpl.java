package com.zy.oes.module.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.RPaperCq;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.paper.service.IRPaperCqService;
import com.zy.oes.module.question.entity.ChoiceQue;
import com.zy.oes.module.question.entity.dto.GetChoiceQuestionConditionDTO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.mapper.ChoiceQueMapper;
import com.zy.oes.module.question.service.IChoiceQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 选择题表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class ChoiceQueServiceImpl extends BaseServiceImpl<ChoiceQueMapper, ChoiceQue> implements IChoiceQueService {

    @Autowired
    private IRPaperCqService cqService;

    @Autowired
    private IPaperService paperService;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public OesPage<ChoiceQueVO> getChoiceQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ChoiceQue> list = this.baseMapper.selectChoiceQueList();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 转换为VO
        OesPage<ChoiceQueVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(entityToVO(list));
        return page;
    }

    @Override
    public OesPage<ChoiceQueVO> getCurrentUserChoiceQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ChoiceQue> list = this.baseMapper.selectList(new QueryWrapper<ChoiceQue>()
                .eq("creator_id", tokenUtil.getCurrentUser().getId())
                .eq("state", 1)
                .eq("is_del", 0));
        OesPage<ChoiceQueVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(entityToVO(list));
        return page;
    }

    @Override
    public OesPage<ChoiceQueVO> getChoiceQuestionPageByCondition(GetChoiceQuestionConditionDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        QueryWrapper<ChoiceQue> wrapper = new QueryWrapper<ChoiceQue>()
                .eq(pageDTO.getId() != null, "id", pageDTO.getId())
                .eq(pageDTO.getCreatorId() != null, "creator_id", pageDTO.getCreatorId())
                .eq(pageDTO.getState() != null, "state", pageDTO.getState());
        if (pageDTO.getTags() != null && pageDTO.getTags().size() > 0) {
            wrapper.like("tags", pageDTO.getTags().get(0));
        }
        List<ChoiceQue> list = this.baseMapper.selectList(wrapper);
        OesPage<ChoiceQueVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(entityToVO(list));
        return page;
    }

    @Override
    public List<ChoiceQueVO> getChoiceQuestionListByPageId(Long paperId) {
        List<ChoiceQue> list = this.baseMapper.selectChoiceQueListByPaperId(paperId);
        if (list.size() == 0) {
            return null;
        }
        // 获取试题分数
        List<RPaperCq> cqs = cqService.getBaseMapper().selectList(new QueryWrapper<RPaperCq>()
                .eq("p_id", paperId)
                .in("q_id", list.stream().map(ChoiceQue::getId).collect(Collectors.toList())));
        // 先排序
        cqs.sort((o1, o2) -> o1.getQId() < o2.getQId() ? 0 : 1);
        // 转换实体类
        return cqsToVO(cqs);
    }

    /**
     * @title entityToVO
     * @description <p> 实体类转换为VO </p>
     * @date 2023/3/20 21:17
     * @author MoZhu
     * @param list 实体类列表
     * @return {@link List<ChoiceQueVO>}
     */
    private List<ChoiceQueVO> entityToVO(List<ChoiceQue> list) {
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<ChoiceQueVO> vos = new ArrayList<>();
        for (ChoiceQue choiceQue : list) {
            ChoiceQueVO vo = new ChoiceQueVO();
            BeanUtils.copyProperties(choiceQue, vo);
            vo.setTags(StringUtil.combineStringToList(choiceQue.getTags()));
            vo.setOpts(StringUtil.combineStringToList(choiceQue.getOpts()));
            vos.add(vo);
        }
        return vos;
    }

    private List<ChoiceQueVO> cqsToVO(List<RPaperCq> cqs) {
        if (cqs.size() == 0) {
            return new ArrayList<>();
        }
        List<ChoiceQueVO> vos = new ArrayList<>();
        for (RPaperCq cq : cqs) {
            ChoiceQueVO vo = new ChoiceQueVO();

            // 查试题
            ChoiceQue que = this.getById(cq.getQId());
            BeanUtils.copyProperties(que, vo);
            vo.setTags(StringUtil.combineStringToList(que.getTags()));
            vo.setOpts(StringUtil.combineStringToList(que.getOpts()));
            vo.setScore(cq.getScore());
            vo.setQNo(cq.getQNo());
            vos.add(vo);
        }
        return vos;
    }
}
