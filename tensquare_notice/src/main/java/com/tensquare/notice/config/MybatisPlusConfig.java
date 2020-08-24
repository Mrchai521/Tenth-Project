package com.tensquare.notice.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/24
 */
@Configuration
//配置Mapper包扫描
@MapperScan("com.tensquare.notice.dao")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor createPaginationInterceptor() {
        return new PaginationInterceptor();
    }
}
