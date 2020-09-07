package com.tensquare.spit;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;

/**
 * @description:吐槽微服务的启动类
 * @author:柴新峰
 * @create:2020/9/7
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }
    //分布式id生成器
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
