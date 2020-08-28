package com.tensquare.notice.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/24
 */
@TableName("tb_notice_fresh")
@Data
@EqualsAndHashCode
public class NoticeFresh {
    private String userId;
    private String noticeId;
}
