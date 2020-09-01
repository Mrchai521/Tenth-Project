package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: 柴新峰
 * @date: 2020-08-31 23:16
 * @description:
 */
@Data
@Entity
@Table(name = "tb_enterprise")
public class Enterprise implements Serializable {
    @Id
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
    private String ishot;
    private String logo;
    /**
     * 职位数
     */
    private int jobcount;

}
