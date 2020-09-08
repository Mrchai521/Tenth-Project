package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @description:
 * @author:柴新峰吐槽微服务的dao
 * @create:2020/9/7
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    /**
     * 根据父级id获取数据
     *
     * @param parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
