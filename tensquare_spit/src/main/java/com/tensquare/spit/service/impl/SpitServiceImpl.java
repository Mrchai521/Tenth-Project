package com.tensquare.spit.service.impl;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private MongoTemplate mongoTemplate;

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
        /**
         * 发布日期
         */
        spit.setPublishtime(new Date());
        /**
         * 浏览量
         */
        spit.setVisits(0);
        /**
         * 分享数
         */
        spit.setShare(0);
        /**
         *  点赞数
         */
        spit.setThumbup(0);
        /**
         * 回复数
         */
        spit.setComment(0);
        /**
         *  状态
         */
        spit.setState("1");

        // 如果当前的吐槽有父节点，那么父节点加一
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
        spitDao.save(spit);
    }
}
