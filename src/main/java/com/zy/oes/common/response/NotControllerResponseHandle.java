package com.zy.oes.common.response;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName:    OnlineExaminationSystem
 * @annotationName: NotControllerResponseHandle
 * @author:         MoZhu
 * @date:           2023/3/6 00:35
 * @description:    <p> 不进行统一响应返回 </p>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseHandle {
}
