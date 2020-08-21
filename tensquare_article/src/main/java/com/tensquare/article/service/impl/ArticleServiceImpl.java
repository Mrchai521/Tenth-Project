package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Article> findAll() {
        return articleDao.selectList(null);
    }

    @Override
    public Article findById(String id) {
        return articleDao.selectById(id);
    }

    @Override
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        EntityWrapper wrapper = new EntityWrapper<Article>();
        wrapper.eq("id", article.getId());
        articleDao.update(article, wrapper);
    }

    @Override
    public void delete(String id) {
        articleDao.deleteById(id);
    }

    @Override
    public Page search(Map map, int page, int size) {
        EntityWrapper wrapper = new EntityWrapper<Article>();
        Set<String> fieldSet = map.keySet();
        for (String field : fieldSet) {
            //wrapper.eq(field, map.get(field));
            wrapper.eq(null != map.get(field), field, map.get(field));
        }
        Page page1 = new Page(page, size);
        List list = articleDao.selectPage(page1, wrapper);
        page1.setRecords(list);
        return page1;
    }
}
