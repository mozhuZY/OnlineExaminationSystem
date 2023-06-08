package com.zy.oes.module.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.util.BeanExpandUtil;
import com.zy.oes.module.answer.entity.Answer;
import com.zy.oes.module.answer.entity.vo.AnswerVO;
import com.zy.oes.module.answer.service.IAnswerService;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.entity.RExamExaminee;
import com.zy.oes.module.exam.entity.dto.EnrollPageDTO;
import com.zy.oes.module.exam.entity.vo.EnrollVO;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import com.zy.oes.module.exam.entity.vo.StudentExamVO;
import com.zy.oes.module.exam.mapper.RExamExamineeMapper;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.exam.service.IRExamExamineeService;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.user.entity.User;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.service.IUserInfoService;
import com.zy.oes.module.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-21
 */
@Service
public class RExamExamineeServiceImpl extends BaseServiceImpl<RExamExamineeMapper, RExamExaminee> implements IRExamExamineeService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IExamService examService;

    @Autowired
    private IPaperService paperService;

    @Autowired
    private IAnswerService answerService;

    @Override
    public OesPage<EnrollVO> getEnrollPage(EnrollPageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        if (userService.getById(pageDTO.getUserId()) == null) {
            throw new ApiException(ErrorCode.APP_ERROR, "用户信息不存在");
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<RExamExaminee> list = this.baseMapper.selectList(new QueryWrapper<RExamExaminee>().eq("examinee_id", pageDTO.getUserId()));
        // 转换VO
        OesPage<EnrollVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(toVO(list));
        return page;
    }

    private List<EnrollVO> toVO(List<RExamExaminee> list) {
        List<EnrollVO> result = new ArrayList<>();
        for (RExamExaminee re : list) {
            EnrollVO vo = new EnrollVO();
            // 报名信息
            vo.setState(re.getState());
            vo.setEnrollTime(re.getCreateTime());

            // 考试信息
            StudentExamVO examVO = new StudentExamVO();
            Exam exam = examService.getById(re.getExamId());
            if (exam == null) {
                continue;
            }
            // 检查考试日期
            examService.checkExamDate(exam);
            BeanUtils.copyProperties(exam, examVO);
            // 创建者姓名
            UserInfo user = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", exam.getCreatorId()));
            if (user != null) {
                examVO.setCreatorName(user.getRealName());
            }
            // 试卷信息
            Paper paper = paperService.getById(exam.getPaperId());
            if (paper == null) {
                continue;
            }
            examVO.setTotalScore(paper.getTotalScore());
            // 考生分数信息
            Answer answer = answerService.getOne(new QueryWrapper<Answer>()
                    .eq("exam_id", re.getExamId())
                    .eq("examinee_id", re.getExamineeId()));
            if (answer != null) {
                examVO.setScore(answer.getTotalScore());
            }

            vo.setExam(examVO);
            result.add(vo);
        }
        return result;
    }
}
