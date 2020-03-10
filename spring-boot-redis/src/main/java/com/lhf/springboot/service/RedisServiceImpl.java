package com.lhf.springboot.service;

import com.lhf.springboot.model.Poetry;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ClassName: RedisServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 15:30
 */

@Service
public class RedisServiceImpl extends RedisService<Poetry> {

    private static final String REDIS_KEY = "poetry-001";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY ;
    }


}

