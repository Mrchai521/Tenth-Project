package com.tensquare.user.service.impl;

import com.tensquare.user.dao.IUserDao;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.IUserService;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description: 用户服务Service
 * @author:柴新峰
 * @create:2020/8/26
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User login(String mobile, String password) {
        User userResult = userDao.findByMobile(mobile);
        if (userResult != null && encoder.matches(password, userResult.getPassword())) {
            return userResult;
        }
        return null;
    }

    @Override
    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        // 初始化
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));
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

    @Override
    public void sendSms(String mobile) {
        String checkCode = RandomStringUtils.randomNumeric(6);
        // 将生成的随机数放在redis缓存中，用与用户的验证
        redisTemplate.opsForValue().set("checkCode" + mobile, checkCode, 6L, TimeUnit.HOURS);
        // 再给RabbitMQ存一份
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("mobile", mobile);
        hashMap.put("checkCode", checkCode);
        redisTemplate.convertAndSend("sms", hashMap);
        System.out.println("验证码为：" + checkCode);
    }

    @Override
    public void removeUser(String id) {
        String token = (String) request.getAttribute("claims_admin");
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("权限不足！");
        }
        userDao.deleteById(id);
    }

    @Override
    public void updateFanscountAndFollowcount(String userId, String friendId, int type) {
        //更新被关注人的粉丝数
        userDao.updateFanscount(type, friendId);
        //更新当前用户的关注数
        userDao.updateFollowcount(type, userId);
    }
}
