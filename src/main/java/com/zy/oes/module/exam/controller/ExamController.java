package com.zy.oes.module.exam.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.entity.dto.ExamDTO;
import com.zy.oes.module.exam.entity.dto.ModifyExamDTO;
import com.zy.oes.module.exam.entity.vo.ExamVO;
import com.zy.oes.module.exam.service.IExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 考试表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "考试接口")
@RestController
@RequestMapping("/api/exam/exam")
public class ExamController {

    @Autowired
    private IExamService service;

    /**
     * @title getExam
     * @description <p> 根据id获取考试详情 </p>
     * @date 2023/3/18 18:30
     * @author MoZhu
     * @param id 考试id
     * @return {@link Exam}
     */
    @ApiOperation("根据id获取考试详情")
    @PostMapping("/get")
    public ExamVO getExam(@RequestParam("examId") Long id) {
        return this.service.getExam(id);
    }

    /**
     * @title getPage
     * @description <p> 分页查询考试信息 </p>
     * @date 2023/3/18 18:28
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<Exam>}
     */
    @ApiOperation("分页查询考试信息")
    @PostMapping("/page/get")
    public PageInfo<ExamVO> getPage(@RequestBody @Valid PageDTO pageDTO) {
        return this.service.getExamPage(pageDTO);
    }

    /**
     * @title addExam
     * @description <p> 新增考试信息 </p>
     * @date 2023/3/18 18:09
     * @author MoZhu
     * @param dto 新增考试DTO
     * @return {@link String}
     */
    @ApiOperation("新增考试信息")
    @PostMapping("/add")
    public String addExam(@RequestBody @Valid ExamDTO dto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(dto, exam);
        exam.setState(0);
        if (this.service.add(exam) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return "新增成功";
    }

    /**
     * @title removeExam
     * @description <p> 删除考试信息 </p>
     * @date 2023/3/18 18:08
     * @author MoZhu
     * @param ids 待删除id列表
     * @return {@link String}
     */
    @ApiOperation("删除考试信息")
    @PostMapping("/remove")
    public String removeExam(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return "删除成功";
    }

    /**
     * @title modifyExam
     * @description <p> 修改考试信息 </p>
     * @date 2023/3/18 18:25
     * @author MoZhu
     * @param dto 修改考试DTO
     * @return {@link String}
     */
    @ApiOperation("修改考试信息")
    @PostMapping("/modify")
    public String modifyExam(@RequestBody @Valid ModifyExamDTO dto) {
        if (this.service.getById(dto.getId()) == null) {
            throw new ApiException(ResultCode.MODIFY_FAIL, "不存在此考试信息");
        }
        Exam exam = new Exam();
        BeanUtils.copyProperties(dto, exam);
        if (this.service.modify(exam) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return "修改成功";
    }
}
