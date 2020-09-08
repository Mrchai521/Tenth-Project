package com.tensquare.spit.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 吐槽服务的控制器
 * @author:柴新峰
 * @create:2020/9/7
 */
@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    /**
     * 通过id查询数据
     *
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    /**
     * 保存数据
     *
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.saveSpit(spit);
        return new Result(true, StatusCode.OK, "保存成功", null);
    }

    /**
     * 修改数据
     *
     * @param spit
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功", null);
    }

    /**
     * 通过id删除数据
     *
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

    /**
     * 通过parentId分页查询数据
     *
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        return new Result(true, StatusCode.OK, "查询成功！", spitService.findByParentId(parentId, page, size));
    }

    @RequestMapping(value = "/thumbup/{spitId}/{userId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId,@PathVariable String userId) {
        //判断当前用户是否已经点赞
        if (redisTemplate.opsForValue().get("thumbup_" + userId) != null) {
            return new Result(false, StatusCode.REPERROR, "不能重复点赞", null);
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userId, 1);
        return new Result(true, StatusCode.OK, "点赞成功", null);
    }
}
