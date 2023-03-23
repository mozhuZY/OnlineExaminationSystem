package com.zy.oes.module.answer.controller;


import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.module.answer.entity.dto.AnswerDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerDetailDTO;
import com.zy.oes.module.answer.entity.dto.GetAnswerPageDTO;
import com.zy.oes.module.answer.entity.dto.ModifyAnswerDTO;
import com.zy.oes.module.answer.entity.vo.AnswerDetailVO;
import com.zy.oes.module.answer.entity.vo.AnswerVO;
import com.zy.oes.module.answer.service.IAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@SuppressWarnings("javadoc")
@Api(tags = "答卷接口")
@RestController
@RequestMapping("/api/answer/answer")
public class AnswerController {

    @Autowired
    private IAnswerService service;

    /**
     * @title getAnswerDetail
     * @description <p> 获取答卷详细信息 </p>
     * @date 2023/3/24 1:24
     * @author MoZhu
     * @param dto 获取答卷详细信息DTO
     * @return {@link AnswerDetailVO}
     */
    @ApiOperation("获取答卷详细信息")
    @GetMapping("/detail/get")
    public AnswerDetailVO getAnswerDetail(@RequestBody @Valid GetAnswerDetailDTO dto) {
        return this.service.getAnswerDetail(dto);
    }

    /**
     * @title getAnswerPage
     * @description <p> 分页获取答卷基础信息 </p>
     * @date 2023/3/24 1:22
     * @author MoZhu
     * @param pageDTO 分页获取答卷基础信息DTO
     * @return {@link PageInfo<AnswerVO>}
     */
    @ApiOperation("分页获取答卷基础信息")
    @GetMapping("/page/get")
    public PageInfo<AnswerVO> getAnswerPage(@RequestBody @Valid GetAnswerPageDTO pageDTO) {
        return this.service.getAnswerPage(pageDTO);
    }

    /**
     * @title submitAnswer
     * @description <p> 提交答卷 </p>
     * @date 2023/3/23 18:54
     * @author MoZhu
     * @param dto 答卷DTO
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("提交答卷")
    @PostMapping("/submit")
    public ApiResult<?> submitAnswer(@RequestBody @Valid AnswerDTO dto) {
        return this.service.addAnswer(dto);
    }

    /**
     * @title removeAnswer
     * @description <p> 删除答卷 </p>
     * @date 2023/3/21 17:29
     * @author MoZhu
     * @param ids 待删除答卷id列表
     * @return {@link ApiResult<?>}
     */
    @ApiOperation("删除答卷")
    @DeleteMapping("/remove")
    public ApiResult<?> removeAnswer(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids) == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.fail("删除失败");
    }

    /**
     * @title modifyAnswer
     * @description <p> 修改答卷分数 </p>
     * @date 2023/3/24 1:20
     * @author MoZhu
     * @param dto 修改答卷分数DTO
     * @return {@link com.zy.oes.common.response.ApiResult<?>}
     */
    @ApiOperation("修改答卷分数")
    @PutMapping("/modify")
    public ApiResult<?> modifyAnswer(@RequestBody @Valid ModifyAnswerDTO dto) {
        return this.service.modifyAnswer(dto);
    }
}
