package com.tensquare.spit.service;

import com.tensquare.spit.pojo.Spit;

import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/7
 */
public interface SpitService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Spit> findAll();

    /**
     * 修改
     *
     * @param spit
     */
    void update(Spit spit);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Spit findById(String id);

    /**
     * 保存
     *
     * @param spit
     */
    void saveSpit(Spit spit);
}
