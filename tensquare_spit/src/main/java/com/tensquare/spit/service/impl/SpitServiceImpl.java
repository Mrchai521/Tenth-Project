package com.tensquare.spit.service.impl;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 吐槽服务Service
 * @author:柴新峰
 * @create:2020/9/7
 */
@Service
public class SpitServiceImpl implements SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    @Override
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    @Override
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    @Override
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    @Override
    public void saveSpit(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spitDao.save(spit);
    }
}
