package com.zy.oes.module.paper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.file.entity.Picture;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.RPaperCq;
import com.zy.oes.module.paper.entity.dto.AddPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.ModifyPaperQuestionDTO;
import com.zy.oes.module.paper.entity.dto.RemovePaperQuestionDTO;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.entity.vo.PaperInfoVO;
import com.zy.oes.module.paper.mapper.PaperMapper;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.paper.service.IRPaperCqService;
import com.zy.oes.module.question.entity.ChoiceQue;
import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;
import com.zy.oes.module.question.entity.vo.SubQueVO;
import com.zy.oes.module.question.service.IBlankQueService;
import com.zy.oes.module.question.service.IChoiceQueService;
import com.zy.oes.module.question.service.IJudgeQueService;
import com.zy.oes.module.question.service.ISubQueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private TokenUtil tokenUtil;

    @Autowired
    private IExamService examService;

    @Autowired
    private IChoiceQueService choiceQueService;

    @Autowired
    private IRPaperCqService rcqService;

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
    public OesPage<PaperInfoVO> getCurrentUserPaperPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        List<Paper> list = this.baseMapper.selectList(new QueryWrapper<Paper>().eq("creator_id", tokenUtil.getCurrentUser().getId()));
        // 转换
        OesPage<PaperInfoVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(toVO(list, true));
        return page;
    }

    @Override
    public ApiResult<Object> addPaperQuestion(AddPaperQuestionDTO dto) {
        // 获取试卷、试题信息
        Paper paper = this.getById(dto.getPaperId());
        RPaperCq rPaperCq = rcqService.getOne(new QueryWrapper<RPaperCq>()
                .eq("p_id", paper.getId())
                .eq("q_id", dto.getQueId())
                .eq("is_del", 0));

        if (rPaperCq != null) {
            throw new ApiException(ResultCode.ADD_FAIL, "试卷中存在相同试题");
        }

        // 添加试卷试题
        if (this.baseMapper.insertQuestion(dto) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }

        // 修改试卷分数
        paper.setTotalScore(paper.getTotalScore().add(BigDecimal.valueOf(dto.getScore())));
        this.modify(paper);
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    @Override
    public ApiResult<Object> removePaperQuestion(RemovePaperQuestionDTO dto) {
        // 获取试卷、试题信息
        Paper paper = this.getById(dto.getPaperId());
        RPaperCq rPaperCq = rcqService.getOne(new QueryWrapper<RPaperCq>().eq("p_id", paper.getId()).eq("q_id", dto.getQueId()));

        // 删除试卷试题
        if (this.baseMapper.deleteQuestion(dto) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }

        // 修改试卷分数
        paper.setTotalScore(paper.getTotalScore().subtract(BigDecimal.valueOf(rPaperCq.getScore())));
        this.modify(paper);
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    @Override
    public ApiResult<Object> modifyPaperQuestion(ModifyPaperQuestionDTO dto) {
        // 获取试卷、试题信息
        Paper paper = this.getById(dto.getPaperId());
        RPaperCq rPaperCq = rcqService.getOne(new QueryWrapper<RPaperCq>()
                .eq("p_id", paper.getId())
                .eq("q_id", dto.getQueId()));

        System.out.println("1 dto:" + dto.getScore() + " qscore:" + rPaperCq.getScore());

        // 修改试卷试题分数
        if (this.baseMapper.updateQuestion(dto) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }

        // 修改试卷分数
        Paper modify = new Paper();
        modify.setId(paper.getId());
        modify.setTotalScore(paper.getTotalScore().add(BigDecimal.valueOf(dto.getScore()).subtract(BigDecimal.valueOf(rPaperCq.getScore()))));
        this.modify(modify);
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

    /**
     * @title toVO
     * @description <p> 实体类转换VO </p>
     * @date 2023/5/3 0:36
     * @author MoZhu
     * @param list 实体类列表
     * @return {@link List<PaperInfoVO>}
     */
    private List<PaperInfoVO> toVO(List<Paper> list, boolean isNeedJudge) {
        List<PaperInfoVO> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for (Paper paper : list) {
            PaperInfoVO vo = new PaperInfoVO();
            BeanUtils.copyProperties(paper, vo);

            if (isNeedJudge) {
                // 判断该试卷可否编辑
                List<Exam> exams = examService.getBaseMapper().selectList(new QueryWrapper<Exam>()
                        .eq("paper_id", paper.getId())
                        .eq("state", 2)
                        .eq("is_del", 0)
                );
                // 不存在与此试卷关联的正在考试的考试，允许编辑，否则不允许编辑
                vo.setEditable(exams.size() <= 0);
            }

            result.add(vo);
        }
        return result;
    }
}
