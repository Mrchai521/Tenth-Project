package com.tensquare.recruit.service.impl;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import com.tensquare.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/1
 */
@Service
@Slf4j
public class RecruitServiceImpl implements RecruitService {
    // 推荐职位的值为2
    private static final String RECOMMED = "2";
    // 关闭职位的值为0
    private static final String CLOSEJOB = "0";
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Recruit> findAllRecommend() {
        return recruitDao.findTop6ByStateOrderByCreatetimeDesc(RECOMMED);
    }

    @Override
    public List<Recruit> newlist() {
        return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc(CLOSEJOB);
    }
}
