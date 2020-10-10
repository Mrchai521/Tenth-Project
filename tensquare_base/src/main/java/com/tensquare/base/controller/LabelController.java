package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-08-30 17:39
 * @description:
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 保存
     *
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "保存成功!", null);
    }

    /**
     * 修改
     *
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Label label) {
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功!", null);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功!", labelService.findAll());
    }

    /**
     * 通过id查询
     *
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId) {
        return new Result(true, StatusCode.OK, "查询成功!", labelService.findById(labelId));
    }

    /**
     * 通过id删除
     *
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功!", null);
    }

    /**
     * @param label
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(true, StatusCode.OK, "查询成功!", list);
    }

    /**
     * 分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/pageSearch/{page}/{size}", method = RequestMethod.POST)
    public Result findPageSearch(@RequestBody Label label, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Label> pageData = labelService.findPageSearch(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功！", new PageResult<Label>((int)pageData.getTotalElements(), pageData.getContent()));
    }
}
