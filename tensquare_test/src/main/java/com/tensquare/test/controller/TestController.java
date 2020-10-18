package com.tensquare.test.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 柴新峰
 * @date: 2020-10-17 15:56
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public Result search() {
        return new Result(true, StatusCode.OK, "查询成功！", testService.search());
    }
}
