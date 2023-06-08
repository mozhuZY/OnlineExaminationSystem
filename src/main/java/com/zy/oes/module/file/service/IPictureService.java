package com.zy.oes.module.file.service;

import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.module.file.entity.GetPicturePageDTO;
import com.zy.oes.module.file.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MoZhu
 * @since 2023-04-21
 */
public interface IPictureService extends IBaseService<Picture> {

    /**
     * @title getPicturePage
     * @description <p> 获取图片分页信息 </p>
     * @date 2023/5/6 0:55
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<Picture>}
     */
    OesPage<Picture> getPicturePage(GetPicturePageDTO pageDTO);
}
