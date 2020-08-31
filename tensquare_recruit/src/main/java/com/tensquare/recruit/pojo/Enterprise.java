package com.tensquare.recruit.pojo;

import lombok.Data;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 23:16
 * @description:
 */
@Data
public class Enterprise {
    private String id;
    private String name;
    private String summary;
    private String address;
    private String labels;
    /**
     * 坐标
     */
    private String coordinate;
    /**
     * 是否热门
     */
    private boolean ishot;
    private String logo;
    /**
     * 职位数
     */
    private int jobcount;

}
