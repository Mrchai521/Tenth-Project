package com.tensquare.qa.client.impl;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.feign.BaseClient;
import org.springframework.stereotype.Component;

/**
 * @author: 柴新峰
 * @date: 2020-10-18 21:03
 * @description:
 */
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器触发了", null);
    }
}
