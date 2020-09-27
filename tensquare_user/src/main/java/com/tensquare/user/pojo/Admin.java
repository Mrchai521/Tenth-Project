package com.tensquare.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: 柴新峰
 * @date: 2020-09-27 22:17
 * @description:
 */
@Entity
@Table(name="tb_admin")
@Data
public class Admin implements Serializable {
    @Id
    private String id;
    /**
     * 登录名
     */
    private  String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    private String state;

}
