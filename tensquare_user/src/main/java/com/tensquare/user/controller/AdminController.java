package com.tensquare.user.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.IAdminService;
import com.tensquare.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author: 柴新峰
 * @date: 2020-09-27 22:20
 * @description:
 */
@RestController
public class AdminController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IAdminService iAdminService;

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result login(@RequestBody Admin admin) {
        Admin adminData = iAdminService.login(admin);
        if (StringUtils.isEmpty(adminData)) {
            //未查询到用户,提示前端登录失败
            return new Result(false, StatusCode.LOGINERROR, "登录失败", null);
        }
        //使得前后端可以知道登录状态的操作,采用jwt生成令牌
        //生成令牌,传递三个参数, 用户的id,登录名,角色信息
        String token = jwtUtil.createJWT(admin.getId(), admin.getLoginName(), "admin");
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);
        hashMap.put("admin", admin);
        return new Result(true, StatusCode.OK, "登录成功", hashMap);
    }

    /**
     * 删除admin
     * @param id
     */
    @RequestMapping(value = "adminDeleteById/{id}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        iAdminService.deleteById(id);
    }

}
