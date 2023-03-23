package com.zy.oes.common.util;

import com.zy.oes.common.base.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: BeanExpandUtil
 * @author: MoZhu
 * @date: 2023/3/21 16:56
 * @description: <p> BeanUtils增强工具类 </p>
 */
@Slf4j
public class BeanExpandUtil {

    /**
     * @title toVOList
     * @description <p> 实体类列表转换为VO对象列表 </p>
     * @date 2023/3/21 17:07
     * @author MoZhu
     * @param list 实体对象列表
     * @param voClass vo类Class对象
     * @return {@link List<E>}
     */
    public static <T extends AbstractEntity, E> List<E> toVOList(List<T> list, Class<?> voClass) {
        List<E> result = new ArrayList<>();
        try {
            for (T t : list) {
                E o = (E) voClass.newInstance();
                BeanUtils.copyProperties(t, o);
                result.add(o);
            }
        } catch (ClassCastException |
                InstantiationException |
                IllegalAccessException e) {
            log.info("VO转换异常 -> " + voClass.getSimpleName());
        }
        return result;
    }
}
