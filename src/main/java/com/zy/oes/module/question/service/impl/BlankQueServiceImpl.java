package com.zy.oes.module.question.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.module.question.entity.BlankQue;
import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.mapper.BlankQueMapper;
import com.zy.oes.module.question.service.IBlankQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return new PageInfo<>(entityToVO(list));
    }

    @Override
    public List<BlankQueVO> getBlankQuestionListByPaperId(Long paperId) {
        List<BlankQue> list = this.baseMapper.selectBlankQueListByPaperId(paperId);
        return entityToVO(list);
    }

    /**
     * @title entityToVO
     * @description <p> 实体类转换为VO </p>
     * @date 2023/3/20 21:24
     * @author MoZhu
     * @param list 实体列表
     * @return {@link List<BlankQueVO>}
     */
    private List<BlankQueVO> entityToVO(List<BlankQue> list) {
        List<BlankQueVO> vos = new ArrayList<>();
        for (BlankQue blankQue : list) {
            BlankQueVO vo = new BlankQueVO();
            BeanUtils.copyProperties(blankQue, vo);
            vo.setTags(StringUtil.combineStringToList(blankQue.getTags()));
            vo.setSpaces(StringUtil.combineStringToList(blankQue.getSpaces()));
            vos.add(vo);
        }
        return vos;
    }
}
