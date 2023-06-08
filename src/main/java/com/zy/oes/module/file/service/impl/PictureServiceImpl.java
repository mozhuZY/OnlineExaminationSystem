package com.zy.oes.module.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.OesPage;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.base.service.impl.BaseServiceImpl;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.response.ErrorCode;
import com.zy.oes.common.response.ResultCode;
import com.zy.oes.module.exam.entity.vo.EnrollVO;
import com.zy.oes.module.file.entity.GetPicturePageDTO;
import com.zy.oes.module.file.entity.Picture;
import com.zy.oes.module.file.mapper.PictureMapper;
import com.zy.oes.module.file.service.IPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MoZhu
 * @since 2023-04-21
 */
@Service
public class PictureServiceImpl extends BaseServiceImpl<PictureMapper, Picture> implements IPictureService {

    @Override
    public OesPage<Picture> getPicturePage(GetPicturePageDTO pageDTO) {
        if (pageDTO.getPageNum() < 1 || pageDTO.getPageSize() < 1) {
            throw new ApiException(ErrorCode.PAGE_ERROR);
        }

        // 设置分页信息
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());

        List<Picture> list = this.baseMapper.selectList(new QueryWrapper<Picture>()
                .eq(pageDTO.getId() != null, "id", pageDTO.getId())
                .eq(pageDTO.getType() != null, "type", pageDTO.getType())
                .eq(pageDTO.getState() != null, "state", pageDTO.getState())
        );
        OesPage<Picture> page = new OesPage<>();
        page.setTotal(new PageInfo<>(list).getTotal());
        page.setList(list);
        return page;
    }
}
