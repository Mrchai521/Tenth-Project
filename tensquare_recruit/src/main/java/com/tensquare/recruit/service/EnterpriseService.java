package com.tensquare.recruit.service;

import com.tensquare.recruit.pojo.Enterprise;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 23:14
 * @description:
 */
public interface EnterpriseService {
    /**
     * 查询所有企业
     */
    List<Enterprise> findAll();

    /**
     * 查询所有热门企业
     *
     * @param isHot
     * @return
     */
    List<Enterprise> findByIshot(String isHot);
}
