package com.tensquare.notice.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.notice.pojo.Notice;
import com.tensquare.notice.pojo.NoticeFresh;
import com.tensquare.notice.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 通知模块的控制器
 * @author:柴新峰
 * @create:2020/8/24
 */
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    /**
     * 根据id查询消息通知
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result selectById(@PathVariable String id) {
        Notice notice = noticeService.selectById(id);
        return new Result(true, StatusCode.OK, "查询成功", notice);
    }

    /**
     * 根据条件分页查询消息通知
     *
     * @param notice
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "search/{page}/{size}", method = RequestMethod.POST)
    public Result selectByList(@RequestBody Notice notice,
                               @PathVariable Integer page,
                               @PathVariable Integer size) {
        Page<Notice> pageData = noticeService.selectByPage(notice, page, size);
        PageResult<Notice> pageResult = new PageResult<>((int) pageData.getTotal(), pageData.getRecords());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 新增通知
     *
     * @param notice
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Notice notice) {
        noticeService.save(notice);
        return new Result(true, StatusCode.OK, "新增成功", null);
    }

    /**
     * 修改通知
     *
     * @param notice
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return new Result(true, StatusCode.OK, "修改成功", null);
    }


    /**
     * 根据用户id查询该用户的待推送消息（新消息）
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "fresh/{userId}/{page}/{size}", method = RequestMethod.GET)
    public Result freshPage(@PathVariable String userId,
                            @PathVariable Integer page,
                            @PathVariable Integer size) {
        Page<NoticeFresh> pageData = noticeService.freshPage(userId, page, size);
        PageResult<NoticeFresh> pageResult = new PageResult<>(
                (int) pageData.getTotal(), pageData.getRecords()
        );
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 删除待推送消息（新消息）
     *
     * @param noticeFresh
     * @return
     */
    @RequestMapping(value = "fresh", method = RequestMethod.DELETE)
    public Result freshDelete(@RequestBody NoticeFresh noticeFresh) {
        noticeService.freshDelete(noticeFresh);
        return new Result(true, StatusCode.OK, "删除成功", null);
    }
}
