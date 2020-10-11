package com.tensquare.user.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.IUserService;
import com.tensquare.utils.JwtUtil;
import com.tensquarre.sms.utils.SmsUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    private IUserService iUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        user = iUserService.login(user.getMobile(), user.getPassword());
        if (user == null) {
            return new Result(false, StatusCode.ERROR, "登录失败", null);
        }
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", user);
        return new Result(true, StatusCode.OK, "登录成功", map);
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
        iUserService.add(user);
        return new Result(true, StatusCode.OK, "注册成功！", null);
    }

    /**
     * 发送注册验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendsms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile) {
        iUserService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功！", null);
    }

    /**
     * 真实发送短信注册验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/sendSmsCode/{mobile}", method = RequestMethod.POST)
    public Result sendSmsCode(@PathVariable String mobile) throws ClientException {
        String checkCode = RandomStringUtils.randomNumeric(6);
        SendSmsResponse sendSmsResponse = smsUtil.sendSms(mobile, "SMS_203675432", "柴先森后台管理系统",
                "{\"code\":\"" + checkCode + "\"}");
        return new Result(true, StatusCode.OK, "发送成功！", sendSmsResponse);
    }

    /**
     * 通过id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "removeUser/{id}", method = RequestMethod.DELETE)
    public Result removeUser(@PathVariable String id) {
        iUserService.removeUser(id);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }
    /**
     * 增加
     * @param user
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody User user){
        iUserService.add(user);
        return new Result(true,StatusCode.OK,"增加成功",null);
    }

    /**
     * 更新粉丝数和关注数
     *
     * @param userId
     * @param friendId
     * @param type
     */
    @RequestMapping(value = "/{userId}/{friendId}/{type}", method = RequestMethod.PUT)
    public void updateFanscountAndFollowcount(@PathVariable String userId, @PathVariable String friendId, @PathVariable int type) {
        iUserService.updateFanscountAndFollowcount(userId, friendId, type);
    }
}
