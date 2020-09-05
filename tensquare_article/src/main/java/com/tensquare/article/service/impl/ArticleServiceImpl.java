package com.tensquare.article.service.impl;

import com.tensquare.article.dao.ArticleJpaDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.StatusCode;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleJpaDao articleJpaDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Article> findAll() {
        return articleJpaDao.findAll();
    }

    @Override
    public Article findById(String id) {
        // 先从缓存中查询当前对象
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        // 若没有取到
        if (article == null) {
            // 从数据库中查询
            article = articleJpaDao.findById(id).get();
            // 存入缓存中
            redisTemplate.opsForValue().set("article_" + id, article, StatusCode.OVERTIME, TimeUnit.SECONDS);
        }
        return article;
    }

    @Override
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleJpaDao.save(article);
        //入库成功后，发送mq消息，内容是消息通知id
//        rabbitTemplate.convertAndSend("article_subscribe", article.getUserid(), article.getId());
    }

    @Override
    public void update(Article article) {
        redisTemplate.delete("article_" + article.getId());
        articleJpaDao.save(article);
    }

    @Override
    public void delete(String id) {
        redisTemplate.delete("article_" + id);
        articleJpaDao.deleteById(id);
    }

    @Override
    public Page<Article> search(Map map, int page, int size) {
        // 创建动态条件
        Specification<Article> specification = createSpecification(map);
        Pageable pageAble = PageRequest.of(page - 1, size);
        Page<Article> articlePage = articleJpaDao.findAll(specification, pageAble);
        return articlePage;
    }

    /**
     * 动态构建条件
     *
     * @param map
     * @return
     */
    private Specification<Article> createSpecification(Map map) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 声明一个集合存储查询条件
                List<Predicate> predicates = new ArrayList<>();
                //ID
                if (map.get("id") != null && !"".equals(map.get("id"))) {
                    predicates.add(criteriaBuilder.like(root.get("id").as(String.class), "%" + map.get("id") + "%"));
                }
                // 专栏ID
                if (map.get("columnid") != null && !"".equals(map.get("columnid"))) {
                    predicates.add(criteriaBuilder.like(root.get("columnid").as(String.class), "%" + map.get("columnid") + "%"));
                }
                // 用户ID
                if (map.get("userid") != null && !"".equals("userid")) {
                    predicates.add(criteriaBuilder.like(root.get("userid").as(String.class), "%" + map.get("userid" + "%")));
                }
                // 标题
                if (map.get("title") != null && !"".equals(map.get("title"))) {
                    predicates.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + (String) map.get("title") + "%"));
                }
                // 文章正文
                if (map.get("content") != null && !"".equals(map.get("content"))) {
                    predicates.add(criteriaBuilder.like(root.get("content").as(String.class), "%" + (String) map.get("content") + "%"));
                }
                // 文章封面
                if (map.get("image") != null && !"".equals(map.get("image"))) {
                    predicates.add(criteriaBuilder.like(root.get("image").as(String.class), "%" + (String) map.get("image") + "%"));
                }
                // 是否公开
                if (map.get("ispublic") != null && !"".equals(map.get("ispublic"))) {
                    predicates.add(criteriaBuilder.like(root.get("ispublic").as(String.class), "%" + (String) map.get("ispublic") + "%"));
                }
                // 是否置顶
                if (map.get("istop") != null && !"".equals(map.get("istop"))) {
                    predicates.add(criteriaBuilder.like(root.get("istop").as(String.class), "%" + (String) map.get("istop") + "%"));
                }
                // 审核状态
                if (map.get("state") != null && !"".equals(map.get("state"))) {
                    predicates.add(criteriaBuilder.like(root.get("state").as(String.class), "%" + (String) map.get("state") + "%"));
                }
                // 所属频道
                if (map.get("channelid") != null && !"".equals(map.get("channelid"))) {
                    predicates.add(criteriaBuilder.like(root.get("channelid").as(String.class), "%" + (String) map.get("channelid") + "%"));
                }
                // URL
                if (map.get("url") != null && !"".equals(map.get("url"))) {
                    predicates.add(criteriaBuilder.like(root.get("url").as(String.class), "%" + (String) map.get("url") + "%"));
                }
                // 类型
                if (map.get("type") != null && !"".equals(map.get("type"))) {
                    predicates.add(criteriaBuilder.like(root.get("type").as(String.class), "%" + (String) map.get("type") + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }


    /**
     * 修改状态（审核）
     *
     * @param id
     */
    @Override
    public void updateState(String id) {
        articleJpaDao.updateState(id);
    }

    /**
     * 点赞文章
     *
     * @param id
     */
    @Override
    public void addThumbup(String id) {
        articleJpaDao.addThumbup(id);
    }
}
