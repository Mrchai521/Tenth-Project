package com.tensquare.article.service.impl;

import com.tensquare.article.dao.ArticleJpaDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.utils.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private RabbitTemplate rabbitTemplate;
    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Override
    public List<Article> findAll() {
        return articleJpaDao.findAll();
    }

    @Override
    public Article findById(String id) {
        Optional<Article> optional = articleJpaDao.findById(id);
        if (optional.isPresent() == true) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleJpaDao.save(article);
        //入库成功后，发送mq消息，内容是消息通知id
        rabbitTemplate.convertAndSend("article_subscribe", article.getUserid(), article.getId());
    }

    @Override
    public void update(Article article) {
        articleJpaDao.save(article);
    }

    @Override
    public void delete(String id) {
        articleJpaDao.deleteById(id);
    }

    @Override
    public Page<Article> search(Map map, int page, int size) {
        Pageable pageAble = PageRequest.of(page - 1, size);
        Page<Article> articlePage = articleJpaDao.findAll(pageAble);
        return articlePage;
    }

   /* public Boolean subscribe(String userId, String articleId) {
        //根据文章id查询文章作者id
        String authorId = articleJpaDao.selectById(articleId).getUserid();
        //创建Rabbit管理器
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        //声明exchange
        DirectExchange exchange = new DirectExchange("article_subscribe");
        rabbitAdmin.declareExchange(exchange);
        //创建queue
        Queue queue = new Queue("article_subscribe_" + userId, true);
        //声明exchange和queue的绑定关系，设置路由键为作者id
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(authorId);
        //存放用户订阅作者
        String userKey = "article_subscribe_" + userId;
        //存放作者的订阅者
        String authorKey = "article_author_" + authorId;
        //查询该用户是否已经订阅作者
        Boolean flag = redisTemplate.boundSetOps(userKey).isMember(authorId);
        if (flag) {
            //如果为flag为true，已经订阅,则取消订阅
            redisTemplate.boundSetOps(userKey).remove(authorId);
            redisTemplate.boundSetOps(authorKey).remove(userId);

            //删除绑定的队列
            rabbitAdmin.removeBinding(binding);
            return false;
        } else {
            // 如果为flag为false，没有订阅，则进行订阅
            redisTemplate.boundSetOps(userKey).add(authorId);
            redisTemplate.boundSetOps(authorKey).add(userId);

            //声明队列和绑定队列
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareBinding(binding);
            return true;
        }
    }*/

    /**
     * 修改状态
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
