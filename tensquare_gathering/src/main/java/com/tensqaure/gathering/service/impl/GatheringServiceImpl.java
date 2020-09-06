package com.tensqaure.gathering.service.impl;

import com.tensqaure.gathering.dao.GatheringDao;
import com.tensqaure.gathering.pojo.Gathering;
import com.tensqaure.gathering.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * @author: 柴新峰
 * @date: 2020-09-06 22:39
 * @description:
 */
@Service
public class GatheringServiceImpl implements GatheringService {
    @Autowired
    private GatheringDao gatheringDao;
    @Override
    public Gathering findById(String id) {
        return gatheringDao.findById(id).get();
    }

    @Override
    @CacheEvict(value = "gathering",key = "#gathering.id")
    public void update(Gathering gathering) {
        gatheringDao.save(gathering);
    }

    @Override
    @CacheEvict(value = "gathering",key = "#id")
    public void deleteById(String id) {
        gatheringDao.deleteById(id);
    }
}
