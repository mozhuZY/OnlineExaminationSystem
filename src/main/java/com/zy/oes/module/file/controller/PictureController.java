package com.zy.oes.module.file.controller;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ApiResult;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.common.util.ApiUtil;
import com.zy.oes.common.util.FileRWUtil;
import com.zy.oes.common.util.TokenUtil;
import com.zy.oes.module.exam.entity.Exam;
import com.zy.oes.module.exam.service.IExamService;
import com.zy.oes.module.file.entity.GetPicturePageDTO;
import com.zy.oes.module.file.entity.ModifyPictureDTO;
import com.zy.oes.module.file.entity.Picture;
import com.zy.oes.module.file.service.IPictureService;
import com.zy.oes.module.user.entity.UserInfo;
import com.zy.oes.module.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MoZhu
 * @since 2023-04-21
 */
@Slf4j
@Api(tags = "图片接口")
@RestController
@RequestMapping("/api/file/picture")
public class PictureController {

    /**
     * 轮播图片路径
     */
    @Value("${oes.picture.banner-picture}")
    private String bannerPath;

    /**
     * 考试图片路径
     */
    @Value("${oes.picture.exam-picture}")
    private String examPath;

    /**
     * 用户图片路径
     */
    @Value("${oes.picture.user-picture}")
    private String userPath;

    @Autowired
    private IPictureService service;

    @Autowired
    private IExamService examService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private TokenUtil tokenUtil;

    @ApiOperation("分页查询图片信息")
    @PostMapping("/page/get")
    public OesPage<Picture> getPicturePage(@RequestBody @Valid GetPicturePageDTO pageDTO) {
        return this.service.getPicturePage(pageDTO);
    }

    @ApiOperation("删除图片")
    @PostMapping("/remove")
    public ApiResult<?> removePicture(@RequestBody @Valid Ids ids) {
        if (this.service.remove(ids)  == 0) {
            throw new ApiException(ResultCode.REMOVE_FAIL);
        }
        return ApiUtil.success("删除成功");
    }

    @ApiOperation("修改图片")
    @PutMapping("/modify")
    public ApiResult<?> modifyPicture(@RequestBody @Valid ModifyPictureDTO dto) {
        Picture picture = new Picture();
        BeanUtils.copyProperties(dto, picture);
        if (this.service.modify(picture)  == 0) {
            throw new ApiException(ResultCode.MODIFY_FAIL);
        }
        return ApiUtil.success("修改成功");
    }

    @ApiOperation("考试图片上传")
    @PostMapping("/exam/upload")
    public ApiResult<?> uploadExamPicture(MultipartFile file, @RequestParam Long examId) {
        // 检查文件
        if (file.isEmpty() || file.getSize() == 0) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }
        // 检查考试是否为空
        Exam exam = examService.getById(examId);
        if (exam == null) {
            throw new ApiException(ResultCode.QUERY_FAIL, "考试信息不存在");
        }
        // 以考试id作为图片名称
        String fileName = FileRWUtil.generateFileName(file, String.valueOf(exam.getId()));
        if (!FileRWUtil.storeFile(file, examPath, fileName)) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }

        // 新增图片数据
        Long pictureId = addPicture("exam/", fileName, 0, 0);

        // 修改考试图片信息
        exam.setPictureId(pictureId);
        examService.modify(exam);
        return ApiUtil.success(ResultCode.SUCCESS, fileName);
    }

    @ApiOperation("用户头像上传")
    @PostMapping("/user/upload")
    public ApiResult<?> uploadUserPicture(MultipartFile file) {
        // 检查文件
        if (file.isEmpty() || file.getSize() == 0) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }

        //获取当前用户id，并转换为字符串
        String id = String.valueOf(tokenUtil.getCurrentUser().getId());

        // 以用户id作为图片名称
        String fileName = FileRWUtil.generateFileName(file, id);
        if (!FileRWUtil.storeFile(file, userPath, fileName)) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }

        // 新增图片数据
        Long pictureId = addPicture("user/", fileName, 0, 0);

        // 修改用户头像信息
        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", tokenUtil.getCurrentUser().getId()));
        userInfo.setPictureId(pictureId);
        userInfoService.modify(userInfo);
        return ApiUtil.success(ResultCode.SUCCESS, fileName);
    }

    @ApiOperation("轮播图上传")
    @PostMapping("/banner/upload")
    public ApiResult<?> uploadBannerPicture(MultipartFile file) {
        // 检查文件
        if (file.isEmpty() || file.getSize() == 0) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }

        // 以用户id作为图片名称
        String fileName = FileRWUtil.generateFileName(file, UUID.randomUUID().toString());
        if (!FileRWUtil.storeFile(file, bannerPath, fileName)) {
            throw new ApiException(ResultCode.FILE_STORE_FAIL);
        }

        // 新增图片数据
        Long pictureId = addPicture("banner/", fileName, 1, 1);
        return ApiUtil.success(ResultCode.SUCCESS, fileName);
    }

    // 新增图片数据
    private Long addPicture(String path, String fileName, int type, int state) {
        Picture picture = new Picture();
        picture.setName(fileName);
        picture.setPath(path);
        picture.setType(type);
        picture.setState(state);
        if (this.service.add(picture) == 0) {
            throw new ApiException(ResultCode.ADD_FAIL, "图片信息新增失败");
        }
        return picture.getId();
    }

//    @ApiOperation("下载文件")
//    @GetMapping("/download")
//    public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
//        // 设置响应头
//        response.setHeader ("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode (fileName, "UTF-8"));
//        FileRWUtil.loadFile(userPath, fileName, response.getOutputStream());
//    }
}
