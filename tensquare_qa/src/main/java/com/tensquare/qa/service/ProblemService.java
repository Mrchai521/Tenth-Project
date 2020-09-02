package com.tensquare.qa.service;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/2
 */
public interface ProblemService {
    /**
     * 最新问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> newList(String labelId, int page, int size);

    /**
     * 热门问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> hotList(String labelId, int page, int size);

    /**
     * 等待问答列表
     *
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    Page<Problem> waitList(String labelId, int page, int size);
}
