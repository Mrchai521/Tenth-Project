package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 用户服务dao
 * @author:柴新峰
 * @create:2020/8/26
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 通过手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile);
}
