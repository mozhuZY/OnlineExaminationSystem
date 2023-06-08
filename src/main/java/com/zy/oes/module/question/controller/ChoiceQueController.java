package com.zy.oes.module.question.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.question.entity.ChoiceQue;
import com.zy.oes.module.question.entity.dto.ChoiceQueDTO;
import com.zy.oes.module.question.entity.dto.GetChoiceQuestionConditionDTO;
import com.zy.oes.module.question.entity.vo.ChoiceQueVO;
import com.zy.oes.module.question.service.IChoiceQueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 选择题表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "选择题接口")
@RestController
@RequestMapping("/api/question/choiceQue")
public class ChoiceQueController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IChoiceQueService service;

    @ApiOperation("根据id查询选择题")
    @GetMapping("/get")
    public ChoiceQueVO getChoiceQuestion(@RequestParam @Valid Long qId) {
        ChoiceQueVO vo = new ChoiceQueVO();
        ChoiceQue choiceQue = this.service.getById(qId);
        BeanUtils.copyProperties(choiceQue, vo);
        // 对字符数组处理
        vo.setTags(StringUtil.combineStringToList(choiceQue.getTags()));
        vo.setOpts(StringUtil.combineStringToList(choiceQue.getOpts()));
        return vo;
    }

    /**
     * @title getChoiceQuestionPage
     * @description <p> 分页查询单选题 </p>
     * @date 2023/3/20 14:35
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    @ApiOperation("分页查询选择题")
    @GetMapping("/page/get")
    public OesPage<ChoiceQueVO> getChoiceQuestionPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getChoiceQuestionPage(pageDTO);
    }

    /**
     * @title getCurrentUserChoiceQuestionPage
     * @description <p> 获取当前用户的选择题信息 </p>
     * @date 2023/5/2 17:20
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    @ApiOperation("获取当前用户的选择题信息")
    @GetMapping("/user/page/get")
    public OesPage<ChoiceQueVO> getCurrentUserChoiceQuestionPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getCurrentUserChoiceQuestionPage(pageDTO);
    }

    /**
     * @title getChoiceQuestionPageByCondition
     * @description <p> 条件筛选获取选择题 </p>
     * @date 2023/5/4 20:58
     * @author MoZhu
     * @param dto 查询条件
     * @return {@link PageInfo<ChoiceQueVO>}
     */
    @ApiOperation("条件筛选获取选择题")
    @GetMapping("/condition/page/get")
    public OesPage<ChoiceQueVO> getChoiceQuestionPageByCondition(GetChoiceQuestionConditionDTO dto) {
        return this.service.getChoiceQuestionPageByCondition(dto);
    }

    /**
     * @title addChoiceQuestion
     * @description <p> 新增选择题 </p>
     * @date 2023/3/20 13:56
     * @author MoZhu
     * @param dto 选择题DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("新增选择题")
    @PostMapping("/add")
    public ApiResult<Object> addChoiceQuestion(@RequestBody @Valid ChoiceQueDTO dto) {
        ChoiceQue choiceQue = new ChoiceQue();
        BeanUtils.copyProperties(dto, choiceQue);
        choiceQue.setCreatorId(tokenUtil.getCurrentUser().getId());
        // 对字符数组处理
        choiceQue.setTags(StringUtil.listToCombineString(dto.getTags()));
        choiceQue.setOpts(StringUtil.listToCombineString(dto.getOpts()));
        if (this.service.add(choiceQue) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    /**
     * @title removeChoiceQue
     * @description <p> 删除选择题 </p>
     * @date 2023/3/20 13:56
     * @author MoZhu
     * @param ids 带删除的选择题id列表
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除选择题")
    @PostMapping("/remove")
    public ApiResult<Object> removeChoiceQue(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    /**
     * @title modifyChoiceQue
     * @description <p> 修改选择题信息 </p>
     * @date 2023/3/20 13:56
     * @author MoZhu
     * @param vo 选择题VO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("修改选择题信息")
    @PutMapping("/modify")
    public ApiResult<Object> modifyChoiceQue(@RequestBody @Valid ChoiceQueVO vo) {
        ChoiceQue choiceQue = new ChoiceQue();
        BeanUtils.copyProperties(vo, choiceQue);
        // 对字符数组处理
        choiceQue.setTags(StringUtil.listToCombineString(vo.getTags()));
        choiceQue.setOpts(StringUtil.listToCombineString(vo.getOpts()));
        if (this.service.modify(choiceQue) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }
}
