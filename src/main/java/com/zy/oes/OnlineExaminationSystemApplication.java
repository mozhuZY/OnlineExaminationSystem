package com.zy.oes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:         MoZhu
 * date:           2023/1/4 20:03
 * description:    OnlineExaminationSystem start class
 */
@SpringBootApplication
@AutoConfiguration
@RestController
public class OnlineExaminationSystemApplication {

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Zhou Ying OnlineExaminationSystem";
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationSystemApplication.class, args);
    }
}
