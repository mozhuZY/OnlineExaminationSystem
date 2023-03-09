package com.zy.oes.common.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.oes.common.base.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName: OnlineExaminationSystem
 * @className: BaseServiceImpl
 * @author: MoZhu
 * @date: 2023/3/9 23:50
 * @description: <p> 基本服务实现类 </p>
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public M getBaseMapper() {
        return baseMapper;
    }
}
