package com.tensquare.user.service;

import com.tensquare.user.pojo.User;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
public interface IUserService {
    /**
     * 用户登录
     */
    User login(String mobile,String password);

    /**
     * 保存用户信息
     *
     * @param user
     */
    void add(User user);

    /**
     * 获取验证码
     *
     * @param mobile
     */
    void sendSms(String mobile);

    /**
     * 通过id删除用户
     *
     * @param id
     */
    void removeUser(String id);

    /**
     *  更新粉丝数和关注数
     * @param userId
     * @param friendId
     * @param type
     */
    void updateFanscountAndFollowcount(String userId, String friendId, int type);
}
