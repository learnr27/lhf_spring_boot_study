package com.lhf.springboot.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName: DynamicDataSource
 * @Author: liuhefei
 * @Description: 动态数据源
 * @Date: 2019/8/28 11:06
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();
    }

    public static enum DatabaseType{
        Master,
        Slave
    }

    public static void master(){
        contextHolder.set(DatabaseType.Master);
    }

    public static void slave(){
        contextHolder.set(DatabaseType.Slave);
    }

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
