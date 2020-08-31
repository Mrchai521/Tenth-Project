package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 23:26
 * @description:
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
    /**
     * 查找热门企业
     *
     * @param ishot
     * @return
     */
    List<Enterprise> findByIshot(String ishot);
}
