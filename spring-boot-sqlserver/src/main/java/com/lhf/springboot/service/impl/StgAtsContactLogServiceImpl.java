package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.StgAtsContactLogRepository;
import com.lhf.springboot.domain.StgAtsContactLog;
import com.lhf.springboot.service.StgAtsContactLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: StgAtsContactLogServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/7 12:20
 */
@Service
public class StgAtsContactLogServiceImpl implements StgAtsContactLogService {
    @Autowired
    private StgAtsContactLogRepository stgAtsContactLogRepository;

    @Override
    public List<StgAtsContactLog> findByAll() {
        return stgAtsContactLogRepository.findAll();
    }

    @Override
    public List<StgAtsContactLog> findByUserId(Integer userId) {
        return stgAtsContactLogRepository.findByUserIdEquals(userId);
    }
}
