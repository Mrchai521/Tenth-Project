package com.tensquare.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.pojo.User;
import com.tensquare.service.IUservice;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Result login(@RequestBody User user){
        User userResult = iUservice.login(user);
        if (userResult != null){
            return new Result(true, StatusCode.OK,"登录成功",userResult);
        }
        return  new Result(false,StatusCode.ERROR,"登录失败",null);
    }
}
