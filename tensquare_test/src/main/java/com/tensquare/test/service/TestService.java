package com.tensquare.test.service;

import com.tensquare.test.Entity.DocTypeConfig;
import com.tensquare.test.Entity.DocTypeConfigVO;

import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-10-17 15:57
 * @description:
 */
public interface TestService {
    List<DocTypeConfigVO> search();
}
