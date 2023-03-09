package com.zy.oes.config.token;

import lombok.Data;

/**
 * @projectName: OnlineExaminationSystem
 * @className: ToKen
 * @author: MoZhu
 * @date: 2023/3/10 1:14
 * @description: <p>  </p>
 */
@Data
public class Token {

    public static final String HEADER = "user-token";

    /**
     * token字符串
     */
    private String token;
}
