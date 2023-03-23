package com.zy.oes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * author:         MoZhu
 * date:           2023/1/4 20:03
 * description:    OnlineExaminationSystem start class
 */
@SpringBootApplication
@EnableAsync
@MapperScan("com.zy.oes.module.*.mapper")
@EnableCaching // 开启注解缓存
public class OnlineExaminationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationSystemApplication.class, args);
    }
}
