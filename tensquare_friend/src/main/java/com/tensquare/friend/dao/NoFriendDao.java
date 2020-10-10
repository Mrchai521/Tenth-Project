package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {
    /**
     * 通过userID和FriendId查询
     *
     * @param userId
     * @param friendId
     * @return
     */
    NoFriend findByUseridAndFriendid(String userId, String friendId);
}
