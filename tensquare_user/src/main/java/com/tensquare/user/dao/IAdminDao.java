package com.tensquare.user.dao;

import com.tensquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 柴新峰
 * @date: 2020-09-27 22:24
 * @description:
 */
public interface IAdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
}
