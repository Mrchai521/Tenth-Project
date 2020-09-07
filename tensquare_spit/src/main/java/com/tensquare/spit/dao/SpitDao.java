package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description:
 * @author:柴新峰吐槽微服务的dao
 * @create:2020/9/7
 */
public interface SpitDao extends MongoRepository<Spit, String> {
}
