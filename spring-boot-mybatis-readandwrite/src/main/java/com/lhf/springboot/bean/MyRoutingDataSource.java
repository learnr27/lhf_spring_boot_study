package com.lhf.springboot.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @ClassName: MyRoutingDataSource
 * @Author: liuhefei
 * @Description: 获取路由key
 * @Date: 2019/8/28 16:54
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
