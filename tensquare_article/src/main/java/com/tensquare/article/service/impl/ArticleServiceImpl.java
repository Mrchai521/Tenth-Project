package com.tensquare.article.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.utils.IdWorker;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

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
        //入库成功后，发送mq消息，内容是消息通知id
        rabbitTemplate.convertAndSend("article_subscribe", article.getUserid(), article.getId());
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

    public Boolean subscribe(String userId, String articleId) {
        //根据文章id查询文章作者id
        String authorId = articleDao.selectById(articleId).getUserid();
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
    }
}
