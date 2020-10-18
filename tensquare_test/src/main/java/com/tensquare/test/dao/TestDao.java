package com.tensquare.test.dao;

import com.tensquare.test.Entity.DocTypeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 柴新峰
 * @date: 2020-10-17 19:11
 * @description:
 */
public interface TestDao extends JpaRepository<DocTypeConfig, Long>, JpaSpecificationExecutor<DocTypeConfig>, Serializable {
    DocTypeConfig findByDocTypeCode(String docTypeCode);

    List<DocTypeConfig> findByParentCode(String docTypeCode);
}
