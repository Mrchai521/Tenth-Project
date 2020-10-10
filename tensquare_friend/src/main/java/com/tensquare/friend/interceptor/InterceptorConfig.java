package com.tensquare.friend.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * 添加拦截器的规则
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //添加jwt的拦截器
        registry.addInterceptor(jwtInterceptor)
                //拦截所有的请求
                .addPathPatterns("/**")
                //放行登录的请求
                .excludePathPatterns("/**/login/**");
    }
}
