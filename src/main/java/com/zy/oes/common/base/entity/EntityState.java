package com.zy.oes.common.base.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @projectName: OnlineExaminationSystem
 * @interfaceName: StateUtil
 * @author: MoZhu
 * @date: 2023/3/21 15:10
 * @description: <p> 抽象实体状态类 </p>
 */
@Getter
@Setter
public class EntityState {

    /**
     * 实体状态
     */
    private int code;

    /**
     * 状态名称
     */
    private String name;

    public EntityState(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
