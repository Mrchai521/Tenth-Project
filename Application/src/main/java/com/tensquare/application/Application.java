package com.tensquare.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/4
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.tensquare.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
