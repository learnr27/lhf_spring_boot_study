package com.lhf.springboot.dao;

import com.lhf.springboot.domain.StgAtsContactLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: StgAtsContactlogRepository
 * @Author: liuhefei
 * @Description: JpaRepository 实现基本查询;  JpaSpecificationExecutor 主要分页查询用到
 * @Date: 2019/8/6 17:16
 */

@Repository
public interface StgAtsContactLogRepository extends JpaRepository<StgAtsContactLog, String> {

    List<StgAtsContactLog> findByUserIdEquals(Integer userId);


}
