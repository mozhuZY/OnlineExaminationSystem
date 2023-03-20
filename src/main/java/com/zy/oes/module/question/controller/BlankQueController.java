package com.zy.oes.module.question.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.StringUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.question.entity.BlankQue;
import com.zy.oes.module.question.entity.dto.BlankQueDTO;
import com.zy.oes.module.question.entity.vo.BlankQueVO;
import com.zy.oes.module.question.service.IBlankQueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 填空题表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "填空题接口")
@RestController
@RequestMapping("/api/question/blankQue")
public class BlankQueController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IBlankQueService service;

    /**
     * @title getBlankQuestion
     * @description <p> 根据id查询填空题 </p>
     * @date 2023/3/20 14:42
     * @author MoZhu
     * @param id 填空题id
     * @return {@link BlankQueVO}
     */
    @ApiOperation("根据id查询填空题")
    @GetMapping("/get")
    public BlankQueVO getBlankQuestion(@RequestParam Long id) {
        BlankQueVO vo = new BlankQueVO();
        BlankQue blankQue = this.service.getById(id);
        if (blankQue == null) {
            throw new ApiException(ResultCode.QUERY_FAIL);
        }
        BeanUtils.copyProperties(blankQue, vo);
        // 对字符列表处理
        vo.setTags(StringUtil.combineStringToList(blankQue.getTags()));
        vo.setSpaces(StringUtil.combineStringToList(blankQue.getSpaces()));
        return vo;
    }

    /**
     * @title getBlankQuestionPage
     * @description <p> 分页查询填空题信息 </p>
     * @date 2023/3/20 14:45
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<BlankQueVO>}
     */
    @ApiOperation("分页查询填空题信息")
    @GetMapping("/page/get")
    public PageInfo<BlankQueVO> getBlankQuestionPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getBlankQuestionPage(pageDTO);
    }

    /**
     * @title addBlankQuestion
     * @description <p> 新增填空题 </p>
     * @date 2023/3/20 13:55
     * @author MoZhu
     * @param dto 填空题DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("新增填空题")
    @PostMapping("/add")
    public ApiResult<Object> addBlankQuestion(@RequestBody @Valid BlankQueDTO dto) {
        BlankQue blankQue = new BlankQue();
        BeanUtils.copyProperties(dto, blankQue);
        blankQue.setCreatorId(tokenUtil.getCurrentUser().getId());
        // 对字符列表处理
        blankQue.setTags(StringUtil.listToCombineString(dto.getTags()));
        blankQue.setSpaces(StringUtil.listToCombineString(dto.getSpaces()));
        if (this.service.add(blankQue) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    /**
     * @title removeBlankQue
     * @description <p> 删除填空题 </p>
     * @date 2023/3/20 13:55
     * @author MoZhu
     * @param ids 待删除的填空题id列表
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除填空题")
    @DeleteMapping("/remove")
    public ApiResult<Object> removeBlankQue(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    /**
     * @title modifyBlankQue
     * @description <p> 修改填空题信息 </p>
     * @date 2023/3/20 13:54
     * @author MoZhu
     * @param vo 填空题VO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("修改选择题")
    @PutMapping("/modify")
    public ApiResult<Object> modifyBlankQue(@RequestBody @Valid BlankQueVO vo) {
        BlankQue blankQue = new BlankQue();
        BeanUtils.copyProperties(vo, blankQue);
        if (this.service.modify(blankQue) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        // 对字符列表处理
        blankQue.setTags(StringUtil.listToCombineString(vo.getTags()));
        blankQue.setSpaces(StringUtil.listToCombineString(vo.getSpaces()));
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }
}
