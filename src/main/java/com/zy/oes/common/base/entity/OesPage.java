package com.zy.oes.common.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @projectName: OnlineExaminationSystem
 * @className: OesPage
 * @author: MoZhu
 * @date: 2023/5/6 15:22
 * @description: <p>  </p>
 */
@Data
@Accessors(chain = true)
@ApiModel("自定义分页类")
public class OesPage<T> implements Serializable {

    @ApiModelProperty("数据列表")
    private List<T> list;

    @ApiModelProperty("总记录数")
    private long total;
}
