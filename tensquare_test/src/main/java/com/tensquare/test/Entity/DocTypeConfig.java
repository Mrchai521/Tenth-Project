package com.tensquare.test.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-10-17 16:00
 * @description:
 */
@Entity
@Table(name = "config")
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class DocTypeConfig implements Serializable {
    @Id
    private Long id;
    /**
     * 类型编码(唯一)
     */
    private String docTypeCode;

    /**
     * 类型名称
     */
    private String docTypeName;

    /**
     * 父级编码
     */
    private String parentCode;

    /**
     * 类型代号
     */
    private String typeNumber;
    private Date create_time;
    private Long creator_id;
    private Long deleted;
    private Long modifier_id;
    private Date modify_time;
    private Long update_count;
    private String dynamic_attrs;
    private String sub_type_name;
}
