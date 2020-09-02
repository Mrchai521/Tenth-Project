package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/2
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    /**
     * 最新问答列表
     *
     * @param labelId
     * @param pageAble
     * @return
     */
    @Query(value = "select * from tb_problem a,tb_pl b where a.id=b.problemid and b.labelid= ? ORDER BY a.replytime DESC", nativeQuery = true)
    Page<Problem> newList(String labelId, Pageable pageAble);

    /**
     * 热门问答列表
     *
     * @param labelId
     * @param pageAble
     * @return
     */
    @Query(value = "select * from tb_problem a,tb_pl b where a.id=b.problemid and b.labelid= ? ORDER BY a.reply DESC", nativeQuery = true)
    Page<Problem> hotList(String labelId, Pageable pageAble);

    /**
     * 等待问答列表
     *
     * @param labelId
     * @param pageAble
     * @return
     */
    @Query(value = "select * from tb_problem a ,tb_pl b WHERE a.id = b.problemid and b.labelid=? and a.reply=0 ORDER BY a.createtime desc", nativeQuery = true)
    Page<Problem> waitList(String labelId, Pageable pageAble);
}
