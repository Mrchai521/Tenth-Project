package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @description: 用户服务dao
 * @author:柴新峰
 * @create:2020/8/26
 */
public interface IUserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 通过手机号查询用户信息
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile);

    /**
     * 修改粉丝的数量信息
     *
     * @param type
     * @param friendId
     */
    @Modifying
    @Query(value = "update tb_user set fanscount=fanscount+? where id=?", nativeQuery = true)
    void updateFanscount(int type, String friendId);

    /**
     * 修改关注的数量信息
     *
     * @param type
     * @param userId
     */
    @Modifying
    @Query(value = "update tb_user set followcount=followcount+? where id=?", nativeQuery = true)
    void updateFollowcount(int type, String userId);
}
