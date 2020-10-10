package com.tensquare.qa.feign;

import com.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:模块调用的接口
 * @author:柴新峰
 * @create:2020/10/10
 */
@FeignClient("tensquare-base")
public interface BaseClient {
    /**
     * 根据id查询标签
     *
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    Result findById(@PathVariable String labelId);
}
