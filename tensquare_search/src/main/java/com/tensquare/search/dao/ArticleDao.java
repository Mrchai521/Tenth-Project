package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description: 文章的dao层
 * @author:柴新峰
 * @create:2020/9/9
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {
    /**
     * 标题或者内容索引查询
     *
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
