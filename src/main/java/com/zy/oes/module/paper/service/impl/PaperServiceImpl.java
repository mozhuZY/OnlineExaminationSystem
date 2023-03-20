package com.zy.oes.module.paper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.dto.AddPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.ModifyPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.RemovePaperQuestionDTO;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.entity.vo.PaperInfoVO;
import com.zy.oes.module.paper.mapper.PaperMapper;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;
import com.zy.oes.module.question.entity.vo.SubQueVO;
import com.zy.oes.module.question.service.IBlankQueService;
import com.zy.oes.module.question.service.IChoiceQueService;
import com.zy.oes.module.question.service.IJudgeQueService;
import com.zy.oes.module.question.service.ISubQueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class PaperServiceImpl extends BaseServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Autowired
    private IChoiceQueService choiceQueService;

    @Autowired
    private IBlankQueService blankQueService;

    @Autowired
    private IJudgeQueService judgeQueService;

    @Autowired
    private ISubQueService subQueService;

    @Override
    public PaperDetailVO getPaperDetail(Long paperId) {
        if (this.baseMapper.selectById(paperId) == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "此试卷不存在");
        }
        PaperDetailVO detail = this.baseMapper.selectPaperDetail(paperId);
        // 获取试题信息
        getQuestionInformation(detail);
        return detail;
    }

    @Override
    public PageInfo<PaperInfoVO> getPaperPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<PaperInfoVO> list = this.baseMapper.selectPaperPage();
        if (list == null || list.size() == 0) {
            throw new ApiException(ResultCode.QUERY_FAIL, "无数据");
        }
        return new PageInfo<>(list);
    }

    @Override
    public ApiResult<Object> addPaperQuestion(AddPaperQuestionDTO dto) {
        if (this.baseMapper.insertQuestion(dto) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    @Override
    public ApiResult<Object> removePaperQuestion(RemovePaperQuestionDTO dto) {
        if (this.baseMapper.deleteQuestion(dto) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    @Override
    public ApiResult<Object> modifyPaperQuestion(ModifyPaperQuestionDTO dto) {
        if (this.baseMapper.updateQuestion(dto) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }

    /**
     * @title getQuestionInformation
     * @description <p> 获取试卷试题信息 </p>
     * @date 2023/3/20 16:10
     * @author MoZhu
     * @param detail 试卷详细信息
     */
    private void getQuestionInformation(PaperDetailVO detail) {
        // 选择题
        detail.setChoiceQues(choiceQueService.getChoiceQuestionListByPageId(detail.getId()));
        // 填空题
        detail.setBlankQues(blankQueService.getBlankQuestionListByPaperId(detail.getId()));
        // 判断题
        detail.setJudgeQues(judgeQueService.getJudgeQuestionListByPaperId(detail.getId()));
        // 主观题
        detail.setSubQues(subQueService.getSubQuestionListByPaperId(detail.getId()));
    }
}
