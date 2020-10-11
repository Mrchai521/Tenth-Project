package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: 柴新峰
 * @date: 2020-10-11 16:54
 * @description:
 */
@FeignClient("tensquare-user")
public interface UserClient {
    /**
     * 更新粉丝数和关注数
     * @param userId
     * @param friendId
     * @param type
     */
    @RequestMapping(value = "/user/{userId}/{friendId}/{type}", method = RequestMethod.PUT)
    public void updateFanscountAndFollowcount(@PathVariable String userId, @PathVariable String friendId, @PathVariable int type);
}
