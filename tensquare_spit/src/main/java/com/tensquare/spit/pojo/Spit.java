package com.tensquare.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:吐槽模块的实体类
 * @author:柴新峰
 * @create:2020/9/7
 */
@Data
public class Spit implements Serializable {
    /**
     * MongoDB的id必须用"_id"来表示
     */
    @Id
    private String _id;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布日期
     */
    private Date publishtime;
    /**
     * 用户id
     */
    private String userid;
    private String nickname;
    /**
     * 浏览量
     */
    private Integer visits;
    /**
     * 点赞数
     */
    private Integer thumbup;
    /**
     * 分享数
     */
    private Integer share;
    /**
     * 回复数
     */
    private Integer comment;
    /**
     * 状态
     */
    private String state;
    /**
     * 父级id
     */
    private String parentid;
}
