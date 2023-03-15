package com.zy.oes.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zy.oes.common.base.entity.AbstractEntity;
import com.zy.oes.common.base.entity.Ids;
import com.zy.oes.common.base.entity.dto.PageDTO;
import com.zy.oes.common.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @projectName: OnlineExaminationSystem
 * @interfaceName: IBaseService
 * @author: MoZhu
 * @date: 2023/3/9 23:47
 * @description: <p> 基本服务接口 </p>
 */
public interface IBaseService<T extends AbstractEntity> extends IService<T> {

    /**
     * @title add
     * @description <p> 通用增加方法 </p>
     * @date 2023/3/13 23:06
     * @author MoZhu
     * @param entity 新增实体类
     * @return {@link int}
     */
    @Transactional(rollbackFor = {Exception.class})
    int add(T entity);

    /**
     * @title remove
     * @description <p> 通用(批量)逻辑删除方法 </p>
     * @date 2023/3/13 23:07
     * @author MoZhu
     * @param ids id列表对象
     * @return {@link int}
     */
    @Transactional(rollbackFor = {ApiException.class})
    int remove(Ids ids) throws ApiException;

    /**
     * @title modify
     * @description <p> 通用修改方法 </p>
     * @date 2023/3/13 23:08
     * @author MoZhu
     * @param entity 修改的实体
     * @return {@link int}
     */
    @Transactional(rollbackFor = {ApiException.class})
    int modify(T entity) throws ApiException;

    /**
     * @title getById
     * @description <p> 根据id查询 </p>
     * @date 2023/3/16 1:37
     * @author MoZhu
     * @param id id
     * @return {@link T}
     */
    T getById(Long id);

    /**
     * @title getPage
     * @description <p> 分页查询 </p>
     * @date 2023/3/16 1:37
     * @author MoZhu
     * @param pageDTO 分页信息
     * @return {@link PageInfo<T>}
     */
    PageInfo<T> getPage(PageDTO pageDTO);
}
