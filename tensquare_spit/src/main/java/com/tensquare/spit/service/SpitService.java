package com.tensquare.spit.service;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * 根据父级id获取数据
     *
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    Page<Spit> findByParentId(String parentId, int page, int size);

    /**
     * 点赞
     * @param spitId
     */
    void thumbup(String spitId);
}
