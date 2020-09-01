package com.tensquare.recruit.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 职位控制器
 * @author:柴新峰
 * @create:2020/9/1
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    /**
     * 查找推荐职位
     * @return
     */
    @RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
    public Result recommend() {
        return new Result(true, StatusCode.OK, "查询成功！", recruitService.findAllRecommend());
    }

    /**
     * 查找最新的职位
     *
     * @return
     */
    @RequestMapping(value = "/search/newlist", method = RequestMethod.GET)
    public Result newlist() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.newlist());
    }
}
