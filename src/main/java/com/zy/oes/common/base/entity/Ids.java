package com.zy.oes.common.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: Ids
 * @author: MoZhu
 * @date: 2023/3/13 22:40
 * @description: <p> 封装id </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("id封装")
public class Ids implements Serializable {

    @ApiModelProperty("id列表")
    @NotNull
    private List<Long> ids;

    /**
     * @title getId
     * @description <p> 获取单个id </p>
     * @date 2023/3/13 22:53
     * @author MoZhu
     * @return {@link java.lang.Long}
     */
    public Long getId() {
        return ids.size() > 0 ? ids.get(0) : null;
    }
}
