package com.tensquare.entity;

import lombok.*;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Result {
    /**
     * 是否成功
     */
    private boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

}
