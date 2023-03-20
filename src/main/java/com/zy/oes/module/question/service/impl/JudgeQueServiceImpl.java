package com.zy.oes.module.question.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.module.question.entity.JudgeQue;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;
import com.zy.oes.module.question.mapper.JudgeQueMapper;
import com.zy.oes.module.question.service.IJudgeQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 判断题表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class JudgeQueServiceImpl extends BaseServiceImpl<JudgeQueMapper, JudgeQue> implements IJudgeQueService {

    @Override
    public PageInfo<JudgeQueVO> getJudgeQuestionPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<JudgeQue> list = this.baseMapper.selectJudgeQueList();
        if (list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        // 转换为VO
        return new PageInfo<>(entityToVO(list));
    }

    @Override
    public List<JudgeQueVO> getJudgeQuestionListByPaperId(Long paperId) {
        List<JudgeQue> list = this.baseMapper.selectJudgeQueListByPaperId(paperId);
        return entityToVO(list);
    }

    /**
     * @title entityToVO
     * @description <p> 实体转换为VO </p>
     * @date 2023/3/20 21:29
     * @author MoZhu
     * @param list 实体类对象列表
     * @return {@link List<JudgeQueVO>}
     */
    private List<JudgeQueVO> entityToVO(List<JudgeQue> list) {
        List<JudgeQueVO> vos = new ArrayList<>();
        for (JudgeQue judgeQue : list) {
            JudgeQueVO vo = new JudgeQueVO();
            BeanUtils.copyProperties(judgeQue, vo);
            vo.setTags(StringUtil.combineStringToList(judgeQue.getTags()));
            vos.add(vo);
        }
        return vos;
    }
}
