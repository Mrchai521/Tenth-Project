package com.tensquare.base.service;

import com.tensquare.base.pojo.Label;

import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-08-30 17:39
 * @description: 标签的service接口
 */
public interface LabelService {
    /**
     * 保存
     * @param label
     */
    void save(Label label);

    /**
     * 修改
     * @param label
     */
    void update(Label label);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 查询所有
     * @return
     */
    List<Label> findAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Label findById(String id);
}
