package com.tensquare.friend.service.impl;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @description: 交友微服务的服务层
 * @author:柴新峰
 * @create:2020/10/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FriendServiceImpl implements IFriendService {
    @Autowired
    private FriendDao friendDao;

    @Override
    public int addFriend(String userId, String friendId) {
        //先判断userid到friendid是否有数据, 如果有数据,就代表是重复添加好友,返回0
        Friend friend = friendDao.findByUserIdAndFriendId(userId, friendId);
        if (!StringUtils.isEmpty(friend)) {
            //有数据,代表重复添加,返回0
            return 0;
        }
        //直接添加好友, 让好友表中userid到friendid的方向type为0
        //此时的friend为null,因此直接new一个接收
        friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setIsLike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据,如果有,把双方的状态都改为1
        if (friendDao.findByUserIdAndFriendId(userId, friendId) != null) {
            //不为null,代表双方都喜欢了,把双方的islike都改为1
            friendDao.updateIsLike("1", userId, friendId);
            friendDao.updateIsLike("1", friendId, userId);
        }
        return 1;
    }
}
