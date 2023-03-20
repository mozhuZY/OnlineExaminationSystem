package com.zy.oes.module.paper.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.paper.entity.Paper;
import com.zy.oes.module.paper.entity.dto.*;
import com.zy.oes.module.paper.entity.vo.PaperDetailVO;
import com.zy.oes.module.paper.entity.vo.PaperInfoVO;
import com.zy.oes.module.paper.service.IPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 试卷表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "试卷接口")
@RestController
@RequestMapping("/api/paper/paper")
public class PaperController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IPaperService service;

    /**
     * @title getPaperDetail
     * @description <p> 根据id查询试卷详细信息 </p>
     * @date 2023/3/19 19:15
     * @author MoZhu
     * @param paperId 试卷id
     * @return {@link PaperDetailVO}
     */
    @ApiOperation("查询试卷详细信息")
    @GetMapping("/detail/get")
    public PaperDetailVO getPaperDetail(@RequestParam("paperId") Long paperId) {
        return this.service.getPaperDetail(paperId);
    }

    /**
     * @title getPaperPage
     * @description <p> 分页查询试卷信息 </p>
     * @date 2023/3/19 19:35
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<PaperInfoVO>}
     */
    @ApiOperation("分页查询试卷信息")
    @GetMapping("/page/get")
    public PageInfo<PaperInfoVO> getPaperPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getPaperPage(pageDTO);
    }

    /**
     * @title addPaper
     * @description <p> 新增试卷 </p>
     * @date 2023/3/19 13:01
     * @author MoZhu
     * @param dto 试卷DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("新增试卷")
    @PostMapping("/add")
    public ApiResult<Object> addPaper(@RequestBody @Valid PaperDTO dto) throws ApiException {
        Paper paper = new Paper();
        BeanUtils.copyProperties(dto, paper);
        paper.setQNum(0);
        paper.setCreatorId(tokenUtil.getCurrentUser().getId());
        if (this.service.add(paper) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    /**
     * @title addPaperQuestion
     * @description <p> 新增试卷试题 </p>
     * @date 2023/3/19 19:42
     * @author MoZhu
     * @param dto 新增试卷试题DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("新增试卷试题")
    @PostMapping("/question/add")
    public ApiResult<Object> addPaperQuestion(@RequestBody @Valid AddPaperQuestionDTO dto) {
        return this.service.addPaperQuestion(dto);
    }

    /**
     * @title removePaper
     * @description <p> 删除试卷 </p>
     * @date 2023/3/19 13:05
     * @author MoZhu
     * @param ids 待删除试卷id列表
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除试卷")
    @DeleteMapping("/remove")
    public ApiResult<Object> removePaper(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    /**
     * @title removePaper
     * @description <p> 删除试卷试题 </p>
     * @date 2023/3/19 19:43
     * @author MoZhu
     * @param dto 删除试卷试题DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除试卷试题")
    @DeleteMapping("/question/remove")
    public ApiResult<Object> removePaperQuestion(@RequestBody @Valid RemovePaperQuestionDTO dto) {
        return this.service.removePaperQuestion(dto);
    }

    /**
     * @title modifyPaperInfo
     * @description <p> 修改试卷基本信息 </p>
     * @date 2023/3/19 13:11
     * @author MoZhu
     * @param dto 修改试卷DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("修改试卷基本信息")
    @PutMapping("/info/modify")
    public ApiResult<Object> modifyPaperInfo(@RequestBody @Valid ModifyPaperInfoDTO dto) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(dto, paper);
        if (this.service.modify(paper) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }

    @ApiOperation("修改试卷试题信息")
    @PutMapping("/question/modify")
    public ApiResult<Object> modifyPaperInfo(@RequestBody @Valid ModifyPaperQuestionDTO dto) {
        return this.service.modifyPaperQuestion(dto);
    }
}
