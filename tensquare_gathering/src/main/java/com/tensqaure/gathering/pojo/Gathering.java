package com.tensqaure.gathering.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: 柴新峰
 * @date: 2020-09-06 22:38
 * @description:
 */
@Entity
@Table(name = "tb_gathering")
@Data
public class Gathering {
    /**
     * 编号
     */
    @Id
    private String id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 大会简介
     */
    private String summary;
    /**
     * 详细说明
     */
    private String detail;
    /**
     * 主办方
     */
    private String sponsor;
    /**
     * 活动图片
     */
    private String image;
    /**
     * 开始时间
     */
    private java.util.Date starttime;
    /**
     * 截止时间
     */
    private java.util.Date endtime;
    /**
     * 举办地点
     */
    private String address;
    /**
     * 报名截止
     */
    private java.util.Date enrolltime;
    /**
     * 是否可见
     */
    private String state;
    /**
     * 城市
     */
    private String city;

}
