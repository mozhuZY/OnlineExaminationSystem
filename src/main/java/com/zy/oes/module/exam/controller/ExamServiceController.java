package com.zy.oes.module.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.entity.RExamExaminee;
import com.zy.oes.module.exam.entity.dto.CancelDTO;
import com.zy.oes.module.exam.entity.dto.EnrollDTO;
import com.zy.oes.module.exam.entity.dto.EnrollPageDTO;
import com.zy.oes.module.exam.entity.vo.EnrollVO;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.exam.service.IRExamExamineeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ExamServiceController
 * @author: MoZhu
 * @date: 2023/3/21 14:18
 * @description: <p> 考试服务接口 </p>
 */
@SuppressWarnings("javadoc")
@Api(tags = "考试服务接口")
@RestController
@RequestMapping("/api/exam/service")
public class ExamServiceController {

    @Autowired
    private IExamService examService;

    @Autowired
    private IRExamExamineeService service;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * @title getEnrollPage
     * @description <p> 分页查询报名信息 </p>
     * @date 2023/3/21 17:18
     * @author MoZhu
     * @param dto 分页查询报名信息DTO
     * @return {@link PageInfo<EnrollVO>}
     */
    @ApiOperation("分页查询考试报名信息")
    @GetMapping("/enroll/page/get")
    public OesPage<EnrollVO> getEnrollPage(@ModelAttribute @Valid EnrollPageDTO dto) {
        return this.service.getEnrollPage(dto);
    }

    /**
     * @title enroll
     * @description <p> 考试报名 </p>
     * @date 2023/3/21 14:54
     * @author MoZhu
     * @param dto 考试报名DTO
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("考试报名")
    @PostMapping("/enroll")
    public ApiResult<?> enroll(@RequestBody @Valid EnrollDTO dto) {
        Exam exam = examService.getById(dto.getExamId());
        if (exam == null) {
            throw new ApiException(ResultCode.ENROLL_FAIL, "此考试不存在");
        }
        Date now = new Date();
        if (now.after(exam.getStartTime())) {
            throw new ApiException(ResultCode.ENROLL_FAIL, "考试已截止报名");
        }
        RExamExaminee rExamExaminee = new RExamExaminee();
        BeanUtils.copyProperties(dto, rExamExaminee);
        rExamExaminee.setExamineeId(tokenUtil.getCurrentUser().getId());
        rExamExaminee.setState(0);
        if (this.service.add(rExamExaminee) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "报名失败");
        }
        return ApiUtil.success("报名成功");
    }

    /**
     * @title unEnroll
     * @description <p> 取消报名 </p>
     * @date 2023/3/21 14:54
     * @author MoZhu
     * @param dto 取消考试报名DTO
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("取消报名")
    @PostMapping("/cancel")
    public ApiResult<?> unEnroll(@RequestBody @Valid CancelDTO dto) {
        Exam exam = examService.getById(dto.getExamId());
        if (exam == null) {
            throw new ApiException(ResultCode.ENROLL_FAIL, "此考试不存在");
        }
        if (this.service.remove(new Ids(exam.getId())) == 0) {
            throw new ApiException(ResultCode.FAIL, "取消失败");
        }
        return ApiUtil.success("报名已取消");
    }

    /**
     * @title detectEnroll
     * @description <p> 通过考试id获取当前用户报名信息 </p>
     * @date 2023/5/5 16:25
     * @author MoZhu
     * @param
     * @return {@link ApiResult}
     */
    @ApiOperation("通过考试id获取当前用户报名信息")
    @GetMapping("/detect")
    public RExamExaminee detectEnroll(@RequestParam("examId") Long examId) {
        Long userId = tokenUtil.getCurrentUser().getId();
        RExamExaminee ree = this.service.getOne(new QueryWrapper<RExamExaminee>().eq("exam_id", examId).eq("examinee_id", userId));
        if (ree == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "未报名本场考试");
        }

        return ree;
    }
}
