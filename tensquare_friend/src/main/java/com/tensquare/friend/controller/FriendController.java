package com.tensquare.friend.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.IFriendService;
import com.tensquare.friend.service.INoFriendService;
import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

/**
 * @description: 交友业务的Controller层
 * @author:柴新峰
 * @create:2020/10/10
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IFriendService iFriendService;
    @Autowired
    private INoFriendService iNoFriendService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 添加好友
     *
     * @param friendId
     * @param type
     * @return
     */
    @RequestMapping(value = "/like/{friendId}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendId, @PathVariable int type) {
        //验证是否登录

        String token = (String) request.getAttribute("claims_user");
        Claims claims = jwtUtil.parseJWT(token);
        if (StringUtils.isEmpty(claims)) {
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足", null);
        }
        //获取当前登录的userid
        String userId = claims.getId();
        //判断是添加好友还是添加非好友
        if (!StringUtils.isEmpty(type)) {
            if (type == 1) {
                // 添加好友
                int flag = iFriendService.addFriend(userId, friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友", null);
                }
                if (flag == 1) {
                    userClient.updateFanscountAndFollowcount(userId, friendId, type);
                    return new Result(true, StatusCode.OK, "添加成功", null);
                }
            } else if ("2".equals(type)) {
                // 添加非好友
                int flag = iNoFriendService.addNoFriend(userId, friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友", null);
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.OK, "添加成功", null);
                }
            }
            return new Result(false, StatusCode.ERROR, "参数异常", null);
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常", null);
        }
    }

    @RequestMapping(value = "/{friendId}", method = RequestMethod.DELETE)
    public Result removeFriend(@PathVariable String friendId) {
        System.out.println(request.getAttribute("claims_user"));
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足", null);
        }
        //得到当前登录的用户id
        String userid = claims.getId();
        iFriendService.deleteFriend(userid, friendId);
        userClient.updateFanscountAndFollowcount(userid, friendId, -1);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }

}
