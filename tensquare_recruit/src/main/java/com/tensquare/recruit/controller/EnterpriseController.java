package com.tensquare.recruit.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 22:59
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/recruit")
public class EnterpriseController {
    private static final String ISTHOT = "1";

   @Autowired
   private EnterpriseService enterpriseService;

    /**
     * 查询所有的企业
     *
     * @return
     */
    @RequestMapping(value = "/search/enterpriseList", method = RequestMethod.GET)
    public Result enterpriseList() {
        return new Result(true, StatusCode.OK, "查询成功！", enterpriseService.findAll());
    }

    /**
     * 查询所有的热门企业
     *
     * @return
     */
    @RequestMapping(value = "/search/hotlist", method = RequestMethod.GET)
    public Result hotList() {
        return new Result(true, StatusCode.OK, "查询成功！", enterpriseService.findByIshot(ISTHOT));
    }
}
