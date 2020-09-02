package com.tensquare.qa.service.impl;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/2
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public Page<Problem> newList(String labelId, int page, int size) {
        Pageable pageAble=PageRequest.of(page-1,size);
        return problemDao.newList(labelId,pageAble);
    }

    @Override
    public Page<Problem> hotList(String labelId, int page, int size) {
        Pageable pageAble=PageRequest.of(page-1,size);
        return problemDao.hotList(labelId,pageAble);
    }

    @Override
    public Page<Problem> waitList(String labelId, int page, int size) {
        Pageable pageAble=PageRequest.of(page-1,size);
        return problemDao.waitList(labelId,pageAble);
    }
}
