package com.tensquare.search.service;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/9
 */
public interface ArticleService {
    /**
     * 保存索引
     *
     * @param article
     */
    void save(Article article);

    /**
     * 通过key查询
     *
     * @param key
     * @param page
     * @param size
     * @return
     */
    Page<Article> findByKey(String key, int page, int size);
}
