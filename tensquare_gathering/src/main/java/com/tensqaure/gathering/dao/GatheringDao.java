package com.tensqaure.gathering.dao;

import com.tensqaure.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: 柴新峰
 * @date: 2020-09-06 22:38
 * @description:
 */
public interface GatheringDao extends JpaRepository<Gathering,String>, JpaSpecificationExecutor<Gathering> {
}
