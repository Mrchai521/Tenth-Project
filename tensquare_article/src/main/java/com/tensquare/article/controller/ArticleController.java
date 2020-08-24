package com.tensquare.article.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        List list = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        Article Article = articleService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", Article);
    }

    /**
     * 新增标签数据接口
     *
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功", null);
    }

    /**
     * 修改标签数据接口
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        articleService.update(article);
        return new Result(true, StatusCode.OK, "修改成功", null);
    }

    /**
     * 删除文章数据接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        articleService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result search(@RequestBody Map map, @PathVariable int page, @PathVariable int size) {
        Page page1 = articleService.search(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult((int) page1.getTotal(), page1.getRecords()));
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public Result exception() throws Exception {
        throw new Exception("测试统一异常处理");
    }
}
