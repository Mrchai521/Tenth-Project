package com.tensquare;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/2
 */
@SpringBootApplication
public class QaApplcation {
    public static void main(String[] args) {
        SpringApplication.run(QaApplcation.class, args);
    }
    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }
}
