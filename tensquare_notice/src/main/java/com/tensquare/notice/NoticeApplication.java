package com.tensquare.notice;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/24
 */
@SpringBootApplication
public class NoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoticeApplication.class);
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}
