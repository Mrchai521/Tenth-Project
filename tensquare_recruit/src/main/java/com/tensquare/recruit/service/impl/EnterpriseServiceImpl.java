package com.tensquare.recruit.service.impl;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 23:14
 * @description:
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public List<Enterprise> findAll() {
        return enterpriseDao.findAll();
    }

    @Override
    public List<Enterprise> findByIshot(String isHot) {
        return enterpriseDao.findByIshot(isHot);
    }
}
