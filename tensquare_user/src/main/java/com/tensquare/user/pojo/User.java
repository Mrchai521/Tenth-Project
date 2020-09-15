package com.tensquare.user.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/26
 */
@Data
@Entity
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    private String id;
    private String mobile;
    private String password;
    private String nickname;
    private String sex;
    private Date birthday;
    private String avatar;
    private String email;
    private Date regdate;
    private Date updatedate;
    private Date lastdate;
    private Long online;
    private String interest;
    private String personality;
    private Integer fanscount;
    private Integer followcount;
}
