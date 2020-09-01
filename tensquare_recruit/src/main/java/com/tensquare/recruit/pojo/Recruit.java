package com.tensquare.recruit.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/9/1
 */
@Data
@Entity
@Table(name = "tb_recruit")
public class Recruit implements Serializable {
    @Id
    private String id;
    /**
     * 职位名称
     */
    private String jobname;
    /**
     * 薪资范围
     */
    private String salary;
    /**
     * 经验要求
     */
    private String condition;
    /**
     * 学历要求
     */
    private String education;
    /**
     * 任职方式
     */
    private String type;
    private String address;
    /**
     * 企业id
     */
    private String eid;
    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    /**
     * 状态
     */
    private String state;
    private String url;
    private String label;
    /**
     * 职位描述
     */
    private String description;
    /**
     * 职位要求
     */
    private String demand;
}
