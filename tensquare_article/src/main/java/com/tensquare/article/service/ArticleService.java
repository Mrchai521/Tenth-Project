package com.tensquare.article.service;

import com.tensquare.article.pojo.Article;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
public interface ArticleService {
    /**
     * 查询所有
     *
     * @return
     */
    List<Article> findAll();

    /**
     * 通过id查询
     */
    Article findById(String id);

    /***
     * 添加文章
     * @param article
     */
    void add(Article article);

    /**
     * 修改文章
     *
     * @param article
     */
    void update(Article article);

    /**
     * 删除文章
     *
     * @param id
     */
    void delete(String id);

    /**
     * 分页查询
     * @param map
     * @param page
     * @param size
     * @return
     */
    Page<Article> search(Map map, int page, int size);

    /**
     * 更新状态
     * @param id
     */
    void updateState(String id);

    /**
     * 点赞文章
     * @param id
     */
    void addThumbup(String id);
}
