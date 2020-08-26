package com.tensquare.user.service.impl;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.IUservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
@Service
public class UserServiceImpl implements IUservice {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.selectOne(user);
    }
}
