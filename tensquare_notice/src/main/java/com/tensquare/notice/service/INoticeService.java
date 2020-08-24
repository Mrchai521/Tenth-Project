package com.tensquare.notice.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/24
 */
public interface INoticeService {
    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Notice selectById(String id);

    /**
     * 分页查询
     *
     * @param notice
     * @param page
     * @param size
     * @return
     */
    Page<Notice> selectByPage(Notice notice, Integer page, Integer size);

    /**
     * 更新消息
     *
     * @param notice
     */
    void updateById(Notice notice);

    /**
     * 保存
     *
     * @param notice
     */
    void save(Notice notice);

    /**
     * fresh消息分页
     */
    Page<NoticeFresh> freshPage(String userId, Integer page, Integer size);

    /**
     * 删除消息
     *
     * @param noticeFresh
     */
    void freshDelete(NoticeFresh noticeFresh);
}
