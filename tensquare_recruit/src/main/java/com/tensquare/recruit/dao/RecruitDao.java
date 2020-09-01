package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/1
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
    /**
     * 查询最新的职位  top6 代表只查询前6个
     *
     * @param state
     * @return
     */
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    /**
     * 查询前6个最新的职位,按照时间倒序排序
     *
     * @param state
     * @return
     */
    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
