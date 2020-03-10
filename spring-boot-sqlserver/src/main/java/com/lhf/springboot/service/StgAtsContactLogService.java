package com.lhf.springboot.service;

import com.lhf.springboot.domain.StgAtsContactLog;

import java.util.List;

/**
 * @ClassName: StgAtsContactlogRepositoryService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/6 17:27
 */
public interface StgAtsContactLogService {

    List<StgAtsContactLog> findByAll();

    List<StgAtsContactLog> findByUserId(Integer userId);
}
