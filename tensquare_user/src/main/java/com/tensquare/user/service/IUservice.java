package com.tensquare.user.service;

import com.tensquare.user.pojo.User;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
public interface IUservice {
    /**
     * 用户登录
     */
    User login(User user);
}
