package com.zy.oes.common.util;

import com.zy.oes.common.base.entity.EntityState;

/**
 * @projectName: OnlineExaminationSystem
 * @className: EntityStateUtil
 * @author: MoZhu
 * @date: 2023/3/21 15:16
 * @description: <p> 实体状态工具类 </p>
 */
public class EntityStateUtil {

    /**
     * @title toCode
     * @description <p> 转换为状态码 </p>
     * @date 2023/3/21 16:33
     * @author MoZhu
     * @param states 状态列表
     * @param stateName 状态名称
     * @return {@link int}
     */
    public static int toCode(EntityState[] states, String stateName) {
        for (EntityState entityState : states) {
            if (stateName.equals(entityState.getName())) {
                return entityState.getCode();
            }
        }
        return -1;
    }

    /**
     * @title toName
     * @description <p> 转换为状态名称 </p>
     * @date 2023/3/21 16:33
     * @author MoZhu
     * @param states 状态列表
     * @param stateCode 状态码
     * @return {@link String}
     */
    public static String toName(EntityState[] states, int stateCode) {
        for (EntityState entityState : states) {
            if (stateCode == entityState.getCode()) {
                return entityState.getName();
            }
        }
        return null;
    }
}
