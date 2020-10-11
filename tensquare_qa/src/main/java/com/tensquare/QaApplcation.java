package com.tensquare;

import com.tensquare.utils.IdWorker;
import com.tensquare.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/2
 */
@SpringBootApplication
@EnableEurekaClient
//发现服务
@EnableDiscoveryClient
// 采取feign的方式发现服务
@EnableFeignClients
public class QaApplcation {
    public static void main(String[] args) {
        SpringApplication.run(QaApplcation.class, args);
    }

    @Bean
    public IdWorker idWorkker() {
        return new IdWorker(1, 1);
    }

    /**
     * 把jwt对象注入容器中
     *
     * @return
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
