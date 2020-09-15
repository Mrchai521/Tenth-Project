package com.tensquare.user.service.impl;

import com.tensquare.user.dao.IUserDao;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.IUservice;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: 用户服务Service
 * @author:柴新峰
 * @create:2020/8/26
 */
@Service
public class UserServiceImpl implements IUservice {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        // 初始化
        // 关注数
        user.setFollowcount(0);
        // 粉丝数
        user.setFanscount(0);
        // 在线时长
        user.setOnline(0L);
        // 注册日期
        user.setRegdate(new Date());
        // 最后登录日期
        user.setLastdate(new Date());
        // 更新日期
        user.setUpdatedate(new Date());
        userDao.save(user);
    }
}
