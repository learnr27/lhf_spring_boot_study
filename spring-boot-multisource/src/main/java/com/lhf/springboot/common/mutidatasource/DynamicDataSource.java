package com.lhf.springboot.common.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: DynamicDataSource
 * @Author: liuhefei
 * @Description: 动态数据源
 * @Date: 2019/7/31 17:37
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
