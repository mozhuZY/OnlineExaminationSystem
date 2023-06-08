package com.zy.oes.module.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.BeanExpandUtil;
import com.zy.oes.common.util.EntityStateUtil;
import com.zy.oes.module.answer.entity.vo.AnswerVO;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.entity.dto.GetExamPageDTO;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import com.zy.oes.module.exam.entity.vo.StudentExamVO;
import com.zy.oes.module.exam.mapper.ExamMapper;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.file.entity.Picture;
import com.zy.oes.module.file.service.IPictureService;
import com.zy.oes.module.paper.service.IPaperService;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 考试表 服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Service
public class ExamServiceImpl extends BaseServiceImpl<ExamMapper, Exam> implements IExamService {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IPictureService pictureService;



    @Autowired
    private IPaperService paperService;

    @Override
    public ExamVO getExam(Long id) {
        Exam exam = this.baseMapper.selectOne(new QueryWrapper<Exam>()
                .eq("id", id)
                .eq("is_del", 0));
        if (exam == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "不存在此考试信息");
        }
        // 考试日期检查
        checkExamDate(exam);
        //转换VO
        ExamVO examVO = new ExamVO();
        BeanUtils.copyProperties(exam, examVO);
        // 获取创建者姓名
        examVO.setCreatorName(userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", exam.getCreatorId())).getRealName());
        // 获取考试图片
        Picture picture = pictureService.getOne(new QueryWrapper<Picture>().eq("id", exam.getPictureId()));
        if (picture != null && picture.getState() == 1) {
            examVO.setPicture(picture.getName());
        } else {
            examVO.setPicture("oes_type_process");
        }
        // 获取试卷分数
        examVO.setTotalScore(paperService.getById(exam.getPaperId()).getTotalScore());
        return examVO;
    }

    @Override
    public OesPage<ExamVO> getExamPage(GetExamPageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        // 查询条件处理
        if (pageDTO.getExamName() != null) {
            pageDTO.setExamName(pageDTO.getExamName().trim());
        }
        if (pageDTO.getSubject() != null) {
            pageDTO.setSubject(pageDTO.getSubject().trim());
        }
        // 查询数据
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Exam> list = this.baseMapper.selectList(new QueryWrapper<Exam>()
                .eq(pageDTO.getId() != null, "id", pageDTO.getId())
                .like(pageDTO.getExamName() != null, "exam_name", pageDTO.getExamName())
                .eq(pageDTO.getCategory() != null, "category", pageDTO.getCategory())
                .eq(pageDTO.getSubject() != null && !"".equals(pageDTO.getSubject()), "subject", pageDTO.getSubject())
                .in(pageDTO.getStates() != null, "state", pageDTO.getStates())
//                .orderBy(true, false, "start_time")
        );
        OesPage<ExamVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(toVO(list));
        return page;
    }

    @Override
    public List<ExamVO> getEnrollExam() {
        List<Exam> exams = this.baseMapper.selectList(new QueryWrapper<Exam>().eq("state", EntityStateUtil.toCode(Exam.states, "已发布")).in( "state", Arrays.asList(1, 2, 3)));
        if (exams.size() > 8) {
            return toVO(exams.subList(0, 8));
        } else {
            return toVO(exams);
        }
    }

    @Override
    public OesPage<ExamVO> getCurrentTeacherPage(PageDTO pageDTO) {
        if (pageDTO.getPageNum() < 0 || pageDTO.getPageSize() < 0) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }
        Long userId = tokenUtil.getCurrentUser().getId();
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        List<Exam> list = this.baseMapper.selectList(new QueryWrapper<Exam>().eq("creator_id", userId));
        OesPage<ExamVO> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(toVO(list));
        return page;
    }

    /**
     * @title toVO
     * @description <p> 转换VO </p>
     * @date 2023/5/1 19:50
     * @author MoZhu
     * @param list Exam对象列表
     * @return {@link List<ExamVO>}
     */
    private List<ExamVO> toVO(List<Exam> list) {
        List<ExamVO> result = new ArrayList<>();
        for (Exam exam : list) {
            // 考试日期检查
            checkExamDate(exam);

            //转换VO
            ExamVO vo = new ExamVO();
            BeanUtils.copyProperties(exam, vo);
            // 获取创建者姓名
            vo.setCreatorName(userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", exam.getCreatorId())).getRealName());
            // 获取考试图片
            Picture picture = pictureService.getOne(new QueryWrapper<Picture>().eq("id", exam.getPictureId()));
            if (picture.getState() != 0) {
                vo.setPicture(picture.getName());
            } else {
                vo.setPicture("oes_type_process");
            }
            // 获取试卷分数
            vo.setTotalScore(paperService.getById(exam.getPaperId()).getTotalScore());
            result.add(vo);
        }
        return result;
    }

    // 检查考试日期数据
    public void checkExamDate(Exam exam) {
        Date now = new Date();
        // 考试开始
//        System.out.println(exam.getId());
//        System.out.println(exam.getStartTime());
//        System.out.println(new Date());
//        System.out.println(exam.getStartTime().before(now));
        if (exam.getState() == 1 && exam.getStartTime().before(now)) {
            exam.setState(2);
            this.baseMapper.updateById(exam);
            System.out.println("考试id" + exam.getId() + "开始考试时间：" + exam.getStartTime());
        }
        // 考试结束
        if (exam.getState() == 2 && exam.getEndTime().before(now)) {
            exam.setState(3);
            this.baseMapper.updateById(exam);
            System.out.println("考试id" + exam.getId() + "结束考试时间：" + exam.getEndTime());
        }
    }
}
