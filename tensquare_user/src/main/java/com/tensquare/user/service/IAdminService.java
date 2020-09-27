package com.tensquare.user.service;

import com.tensquare.user.pojo.Admin;

/**
 * @author: 柴新峰
 * @date: 2020-09-27 22:22
 * @description:
 */
public interface IAdminService {
    /**
     * 登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
