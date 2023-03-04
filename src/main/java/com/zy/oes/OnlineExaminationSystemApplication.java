package com.zy.oes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * author:         MoZhu
 * date:           2023/1/4 20:03
 * description:    OnlineExaminationSystem start class
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.zy.oes.module.*.mapper")
public class OnlineExaminationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationSystemApplication.class, args);
    }
}
