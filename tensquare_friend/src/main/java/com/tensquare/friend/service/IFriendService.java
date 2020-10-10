package com.tensquare.friend.service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
public interface IFriendService {
    /**
     * 添加好友
     * @param userId
     * @param friendId
     * @return
     */
    int addFriend(String userId,String friendId);
}
