package com.tensquare.search.service.impl;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/9
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findByTitleOrContentLike(key, key, pageable);
    }
}
