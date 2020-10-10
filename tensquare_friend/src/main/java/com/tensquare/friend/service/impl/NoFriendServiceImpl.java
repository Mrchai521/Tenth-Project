package com.tensquare.friend.service.impl;

import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.NoFriend;
import com.tensquare.friend.service.INoFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/10/10
 */
@Service
public class NoFriendServiceImpl implements INoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;

    @Override
    public int addNoFriend(String userId, String friendId) {
        //通过数据库查询是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendId);
        if (noFriend != null) {
            //代表是重复添加
            return 0;
        }
        //不是好友
        noFriend = new NoFriend();
        noFriend.setFriendid(friendId);
        noFriend.setUserid(userId);
        noFriendDao.save(noFriend);
        return 1;
    }
}
