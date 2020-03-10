package com.lhf.springboot.ehcache.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: EhcacheConfig
 * @Author: liuhefei
 * @Description: 缓存事件监听器，用于记录系统操作缓存数据的情况，最快的方法是实现CacheEventListener接口
 * @Date: 2019/9/11 15:58
 */
public class EhcacheConfig implements CacheEventListener<Object,Object> {


    private static final Logger logger = LoggerFactory.getLogger(EhcacheConfig.class);

    @Override
    public void onEvent(CacheEvent cacheEvent) {

        logger.info("caching event {} {} {} {}",

                cacheEvent.getType(),

                cacheEvent.getKey(),

                cacheEvent.getOldValue(),

                cacheEvent.getNewValue());

    }
}
