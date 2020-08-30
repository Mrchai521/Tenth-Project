package com.tensquare.base.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: 柴新峰
 * @date: 2020-08-30 17:39
 * @description: 标签的实体类
 */
@Entity
@Table(name = "tb_label")
@Data
public class Label implements Serializable {
    @Id
    private String id;
    /**
     * 标签的名称
     */
    private String labelname;
    /**
     * 状态
     */
    private String state;
    /**
     * 使用的数量
     */
    private Long count;
    /**
     * 关注数
     */
    private Long fans;
    /**
     * 是否推荐
     */
    private String recommend;

}
