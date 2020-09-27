package com.tensquare.user.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.IUservice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUservice iUservice;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        User userResult = iUservice.login(user);
        if (userResult != null) {
            return new Result(true, StatusCode.OK, "登录成功", userResult);
        }
        return new Result(false, StatusCode.ERROR, "登录失败", null);
    }

    /**
     * 用户注册
     *
     * @param code
     * @param user
     * @return
     */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result regist(@PathVariable String code, @RequestBody User user) {
        // 获取缓存中的验证码
        String checkCodeRedis = (String) redisTemplate.opsForValue().get("checkCode" + user.getMobile());
        if (StringUtils.isEmpty(checkCodeRedis)) {
            // 从redis中获取验证码为空，代表没有先获取验证码
            return new Result(false, StatusCode.ERROR, "请先获取验证码", null);
        }
        if (!checkCodeRedis.equals(code)) {
            // 如果输入的验证码不正确，那么也直接返回该方法
            return new Result(false, StatusCode.ERROR, "请输入验证码", null);
        }
        // 验证码正确，保存用户信息
        iUservice.add(user);
        return new Result(true, StatusCode.OK, "注册成功！", null);
    }

    /**
     * 发送注册验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile){
        iUservice.sendSms(mobile);
        return new Result(true,StatusCode.OK,"发送成功！",null);
    }
}
