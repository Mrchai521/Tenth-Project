package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @description: 交友模块的dao层
 * @author:柴新峰
 * @create:2020/10/10
 */
public interface FriendDao extends JpaRepository<Friend, String> {
    /**
     * 通过userid和friendId查询数据
     *
     * @param userId
     * @param friendId
     * @return
     */
    Friend findByUserIdAndFriendId(String userId, String friendId);

    @Modifying
    @Query(value = "update  tb_friend set islike=? where userid=? and friendid = ?", nativeQuery = true)
    void updateIsLike(String s, String userId, String friendId);
}
