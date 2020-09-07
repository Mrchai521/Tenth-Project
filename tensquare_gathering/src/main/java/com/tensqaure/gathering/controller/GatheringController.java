package com.tensqaure.gathering.controller;

import com.tensqaure.gathering.pojo.Gathering;
import com.tensqaure.gathering.service.GatheringService;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 柴新峰
 * @date: 2020-09-06 22:37
 * @description:
 */
@RestController
@RequestMapping("/gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功！", gatheringService.findById(id));
    }

    /**
     * 修改
     *
     * @param gathering
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Gathering gathering) {
        gatheringService.update(gathering);
        return new Result(true, StatusCode.OK, "修改成功！", null);
    }

    /**
     * 通过id删除信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        gatheringService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功！", null);
    }
}
