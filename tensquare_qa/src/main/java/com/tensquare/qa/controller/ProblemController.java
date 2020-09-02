package com.tensquare.qa.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 问答控制器
 * @author:柴新峰
 * @create:2020/9/2
 */
@RestController
@CrossOrigin
@RequestMapping("/prolem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    /**
     * 最新问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result newList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.newList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功！", new PageResult<Problem>((int) problemPage.getTotalElements(), problemPage.getContent()));
    }

    /**
     * 热门问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/hotList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result hotList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.hotList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功！", new PageResult<Problem>((int) problemPage.getTotalElements(), problemPage.getContent()));
    }

    /**
     * 等待问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/waitList/{labelId}/{page}/{size}", method = RequestMethod.GET)
    public Result waitList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        Page<Problem> problemPage = problemService.waitList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功！", new PageResult<Problem>((int) problemPage.getTotalElements(), problemPage.getContent()));
    }
}
