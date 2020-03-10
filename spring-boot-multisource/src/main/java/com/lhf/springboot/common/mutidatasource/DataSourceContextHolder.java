package com.lhf.springboot.common.mutidatasource;

/**
 * @ClassName: DataSourceContextHolder
 * @Author: liuhefei
 * @Description: datasource的上下文
 * @Date: 2019/7/31 17:40
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType  数据源类型
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
