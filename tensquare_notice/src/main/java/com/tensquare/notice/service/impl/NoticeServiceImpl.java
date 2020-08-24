package com.tensquare.notice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.notice.dao.NoticeDao;
import com.tensquare.notice.dao.NoticeFreshDao;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;
import com.tensquare.notice.service.INoticeService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/24
 */
@Service
public class NoticeServiceImpl implements INoticeService {
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private NoticeFreshDao noticeFreshDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public Notice selectById(String id) {
        return noticeDao.selectById(id);
    }

    @Override
    public Page<Notice> selectByPage(Notice notice, Integer page, Integer size) {
        // 封装分页对象
        Page<Notice> pageData = new Page<>(page, size);
        // 执行分页查询
        List<Notice> noticeList = noticeDao.selectPage(pageData, new EntityWrapper<>(notice));
        // 设置结果集到分页对象中
        pageData.setRecords(noticeList);

        return pageData;
    }

    @Override
    public void updateById(Notice notice) {
        noticeDao.updateById(notice);
    }

    @Override
    public void save(Notice notice) {
        // 设置初始值(设置状态 0表示未读，1表示已读)
        notice.setState("0");
        notice.setCreatetime(new Date());
        //使用分布式id生成器，生成id
        String id = idWorker.nextId() + "";
        notice.setId(id);
        noticeDao.insert(notice);
        // 待推送消息入库，新消息提醒
        NoticeFresh noticeFresh = new NoticeFresh();
        // 设置消息id
        noticeFresh.setNoticeId(id);
        noticeFresh.setUserId(notice.getReceiverId());
        noticeFreshDao.insert(noticeFresh);
    }

    @Override
    public Page<NoticeFresh> freshPage(String userId, Integer page, Integer size) {
        // 封装查询条件
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setUserId(userId);
        Page<NoticeFresh> noticeFreshPage = new Page<>(page, size);
        // 执行查询
        List<NoticeFresh> list = noticeFreshDao.selectPage(noticeFreshPage, new EntityWrapper<>(noticeFresh));
        //设置查询结果集到分页对象中
        noticeFreshPage.setRecords(list);
        return noticeFreshPage;
    }

    @Override
    public void freshDelete(NoticeFresh noticeFresh) {
        noticeFreshDao.delete(new EntityWrapper<>(noticeFresh));
    }
}
