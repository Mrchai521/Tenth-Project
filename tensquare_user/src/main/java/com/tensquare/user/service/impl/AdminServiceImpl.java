package com.tensquare.user.service.impl;

import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 柴新峰
 * @date: 2020-09-27 22:23
 * @description:
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Admin login(Admin admin) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        //由于有了拦截器,因此直接从拦截器的request中获取admin角色的token
        String token = (String) request.getAttribute("claims_admin");
        //如果获取的token为空,那么就代表权限不足
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("权限不足!");
        }
        //上面的权限信息验证完成后, 进行用户的删除
    }
}
