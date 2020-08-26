package com.tensquare.user.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
@Configuration
@MapperScan("com.tensquare.user.dao")
public class MybatisConfig {
    public class MybatisPlusConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }
    }
}
