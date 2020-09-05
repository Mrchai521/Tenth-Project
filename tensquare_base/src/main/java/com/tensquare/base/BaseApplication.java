package com.tensquare.base;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description: 基础服务的启动类
 * @author:柴新峰
 * @create:2020/8/28
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

//    @Bean
//    public IdWorker idWorker() {
//        return new IdWorker(1, 1);
//    }
}
