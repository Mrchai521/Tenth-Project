package com.tensquare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author:柴新峰
 * @create:2020/8/21
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class PageResult<T> {
    private int total;
    private List<T> rows;
}
