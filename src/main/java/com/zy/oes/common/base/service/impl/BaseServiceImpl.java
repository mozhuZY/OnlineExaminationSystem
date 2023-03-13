package com.zy.oes.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.oes.common.base.entity.AbstractEntity;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.service.IBaseService;
import com.zy.oes.common.exception.ApiException;
import com.zy.oes.common.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @projectName: OnlineExaminationSystem
 * @className: BaseServiceImpl
 * @author: MoZhu
 * @date: 2023/3/9 23:50
 * @description: <p> 基本服务实现类 </p>
 */
@SuppressWarnings("all")
public class BaseServiceImpl<M extends BaseMapper<T>, T extends AbstractEntity> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    protected TokenUtil tokenUtil;

    @Autowired
    protected M baseMapper;

    @Override
    public M getBaseMapper() {
        return baseMapper;
    }

    @Override
    public int add(T entity) {
        entity.setId(null);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(entity.getCreateTime());
        entity.setIsDel(false);
        return this.baseMapper.insert(entity);
    }

    @Override
    public int remove(Ids ids) throws ApiException {
        if (ids.getIds() == null || ids.getIds().size() == 0) {
            throw new ApiException("id不能为空");
        }
        // 批量逻辑删除
        return this.baseMapper.update(null, new UpdateWrapper<T>()
                .in("id", ids.getIds())
                .eq("is_del", false));
    }

    @Override
    public int modify(T entity) throws ApiException {
        if (entity.getId() == null) {
            throw new ApiException("id不能为空");
        }
        // 防止修改通用字段
        entity.setCreateTime(null);
        // 更新字段修改时间
        entity.setUpdateTime(new Date());
        return this.baseMapper.updateById(entity);
    }
}
