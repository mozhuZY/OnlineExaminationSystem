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
import com.zy.oes.module.question.entity.JudgeQue;
import com.zy.oes.module.question.entity.dto.JudgeQueDTO;
import com.zy.oes.module.question.entity.vo.JudgeQueVO;
import com.zy.oes.module.question.service.IJudgeQueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 判断题表 前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "判断题接口")
@RestController
@RequestMapping("/api/question/judgeQue")
public class JudgeQueController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IJudgeQueService service;

    /**
     * @title getJudgeQuestion
     * @description <p> 根据id查询判断题 </p>
     * @date 2023/3/20 14:53
     * @author MoZhu
     * @param qId 判断题id
     * @return {@link JudgeQueVO}
     */
    @ApiOperation("根据id查询判断题")
    @GetMapping("/get")
    public JudgeQueVO getJudgeQuestion(@RequestParam("qId") Long qId) {
        JudgeQueVO vo = new JudgeQueVO();
        JudgeQue judgeQue = this.service.getById(qId);
        if (judgeQue == null) {
            throw new ApiException(ResultCode.QUERY_FAIL);
        }
        BeanUtils.copyProperties(judgeQue, vo);
        // 对字符列表处理
        vo.setTags(StringUtil.combineStringToList(judgeQue.getTags()));
        return vo;
    }

    /**
     * @title getJudgeQuestionPage
     * @description <p> 分页查询判断题 </p>
     * @date 2023/3/20 14:53
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<JudgeQueVO>}
     */
    @ApiOperation("分页查询判断题")
    @GetMapping("/page/get")
    public PageInfo<JudgeQueVO> getJudgeQuestionPage(@ModelAttribute @Valid PageDTO pageDTO) {
        return this.service.getJudgeQuestionPage(pageDTO);
    }

    /**
     * @title addJudgeQuestion
     * @description <p> 新增判断题 </p>
     * @date 2023/3/20 13:53
     * @author MoZhu
     * @param dto 判断题DTO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("新增判断题")
    @PostMapping("/add")
    public ApiResult<Object> addJudgeQuestion(@RequestBody @Valid JudgeQueDTO dto) {
        JudgeQue judgeQue = new JudgeQue();
        BeanUtils.copyProperties(dto, judgeQue);
        judgeQue.setCreatorId(tokenUtil.getCurrentUser().getId());
        // 对字符列表处理
        judgeQue.setTags(StringUtil.listToCombineString(dto.getTags()));
        if (this.service.add(judgeQue) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL);
        }
        return ApiUtil.success(ResultCode.ADD_SUCCESS);
    }

    /**
     * @title removeJudgeQue
     * @description <p> 删除判断题 </p>
     * @date 2023/3/20 13:53
     * @author MoZhu
     * @param ids 待删除的判断题列表
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("删除判断题")
    @DeleteMapping("/remove")
    public ApiResult<Object> removeJudgeQue(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success(ResultCode.REMOVE_SUCCESS);
    }

    /**
     * @title modifyJudgeQue
     * @description <p> 修改判断题信息 </p>
     * @date 2023/3/20 13:52
     * @author MoZhu
     * @param vo 判断题VO
     * @return {@link ApiResult<Object>}
     */
    @ApiOperation("修改判断题信息")
    @PutMapping("/modify")
    public ApiResult<Object> modifyJudgeQue(@RequestBody @Valid JudgeQueVO vo) {
        JudgeQue judgeQue = new JudgeQue();
        BeanUtils.copyProperties(vo, judgeQue);
        // 对字符列表处理
        judgeQue.setTags(StringUtil.listToCombineString(vo.getTags()));
        if (this.service.modify(judgeQue) == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success(ResultCode.MODIFY_SUCCESS);
    }
}
