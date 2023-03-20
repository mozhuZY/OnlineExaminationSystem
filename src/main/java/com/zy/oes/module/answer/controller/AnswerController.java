package com.zy.oes.module.answer.controller;


import com.zy.oes.common.response.ApiResult;
import com.zy.oes.module.answer.service.IAnswerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-03-04
 */
@Api(tags = "答卷接口")
@RestController
@RequestMapping("/api/answer/answer")
public class AnswerController {

    @Autowired
    private IAnswerService service;

    public ApiResult<Object> addAnswer() {
        return null;
    }
}
