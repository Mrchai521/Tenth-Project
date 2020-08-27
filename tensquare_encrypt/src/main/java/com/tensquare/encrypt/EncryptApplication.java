package com.tensquare.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description: 网关的启动类
 * @author:柴新峰
 * @create:2020/8/27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class EncryptApplication {
    public static void main(String[] args) {
        SpringApplication.run(EncryptApplication.class, args);
    }
}
