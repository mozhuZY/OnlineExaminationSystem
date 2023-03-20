package com.zy.oes.module.question.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.module.question.entity.ChoiceQue;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.mapper.ChoiceQueMapper;
import com.zy.oes.module.question.service.IChoiceQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageInfo<ChoiceQueVO> getChoiceQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<ChoiceQue> list = this.baseMapper.selectChoiceQueList();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 转换为VO
        return new PageInfo<>(entityToVO(list));
    }

    @Override
    public List<ChoiceQueVO> getChoiceQuestionListByPageId(Long paperId) {
        List<ChoiceQue> list = this.baseMapper.selectChoiceQueListByPaperId(paperId);
        return entityToVO(list);
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
}
