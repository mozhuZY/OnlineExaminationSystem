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
import com.zy.oes.module.question.entity.SubQue;
import com.zy.oes.module.question.entity.dto.SubQueDTO;
import com.zy.oes.module.question.entity.vo.SubQueVO;
import com.zy.oes.module.question.service.ISubQueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 主观题表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "主观题接口")
@RestController
@RequestMapping("/api/question/subQue")
public class SubQueController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ISubQueService service;

    /**
     * @title getSubQuestion
     * @description <p> 根据id查询主观题 </p>
     * @date 2023/3/20 15:00
     * @author MoZhu
     * @param qId 主观题id
     * @return {@link SubQueVO}
     */
    @ApiOperation("根据id查询主观题")
    @PostMapping("/get")
    public SubQueVO getSubQuestion(@RequestParam("qId") Long qId) {
        SubQueVO vo = new SubQueVO();
        SubQue subQue = this.service.getById(qId);
        if (subQue == null) {
            throw new ApiException(ResultCode.QUERY_FAIL);
        }
        BeanUtils.copyProperties(subQue, vo);
        // 对字符列表处理
        vo.setTags(StringUtil.combineStringToList(subQue.getTags()));
        return vo;
    }

    /**
     * @title getSubQuestionPage
     * @description <p> 分页查询主观题 </p>
     * @date 2023/3/20 15:00
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<SubQueVO>}
     */
    @ApiOperation("分页查询主观题")
    @PostMapping("/page/get")
    public PageInfo<SubQueVO> getSubQuestionPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getSubQuestionPage(pageDTO);
    }

    /**
     * @title addSubQuestion
     * @description <p> 新增主观题 </p>
     * @date 2023/3/20 13:51
     * @author MoZhu
     * @param  dto 主观题DTO
     * @return {@link ApiResult<Object>}
     */
    @SuppressWarnings("all")
    @ApiOperation("新增主观题")
    @PostMapping("/add")
    public ApiResult<Object> addSubQuestion(@RequestBody @Valid SubQueDTO dto) {
        SubQue subQue = new SubQue();
        BeanUtils.copyProperties(dto, subQue);
        subQue.setCreatorId(tokenUtil.getCurrentUser().getId());
        // 对字符列表处理
        subQue.setTags(StringUtil.listToCombineString(dto.getTags()));
        if (this.service.add(subQue) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    /**
     * @title removeSubQue
     * @description <p> 删除主观题 </p>
     * @date 2023/3/20 13:52
     * @author MoZhu
     * @param ids 待删除主观题id列表
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除主观题")
    @DeleteMapping("/remove")
    public ApiResult<Object> removeSubQue(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    /**
     * @title modifySubQue
     * @description <p> 修改主观题信息 </p>
     * @date 2023/3/20 13:52
     * @author MoZhu
     * @param vo 主观题VO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("修改主观题信息")
    @PutMapping("/modify")
    public ApiResult<Object> modifySubQue(@RequestBody @Valid SubQueVO vo) {
        SubQue subQue = new SubQue();
        BeanUtils.copyProperties(vo, subQue);
        // 对字符列表处理
        subQue.setTags(StringUtil.listToCombineString(vo.getTags()));
        if (this.service.modify(subQue) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }
}
