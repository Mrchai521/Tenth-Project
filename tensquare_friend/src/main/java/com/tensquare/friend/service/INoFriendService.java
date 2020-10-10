package com.tensquare.friend.service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
public interface INoFriendService {
    /**
     * 添加非好友
     */
    int addNoFriend(String userId,String friend);
}
