package com.tensquare.recruit.service;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/1
 */
public interface RecruitService {
    /**
     * 查询所有推荐的职位
     *
     * @return
     */
    List<Recruit> findAllRecommend();

    /**
     * 查询热门职位
     *
     * @return
     */
    List<Recruit> newlist();
}
