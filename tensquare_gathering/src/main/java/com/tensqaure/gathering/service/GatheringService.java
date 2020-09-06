package com.tensqaure.gathering.service;

import com.tensqaure.gathering.pojo.Gathering;

/**
 * @author: 柴新峰
 * @date: 2020-09-06 22:39
 * @description:
 */
public interface GatheringService {
    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Gathering findById(String id);
    /**
     * 修改
     */
     void update(Gathering gathering);
    /**
     * 删除
     */
    void deleteById(String id);
}
