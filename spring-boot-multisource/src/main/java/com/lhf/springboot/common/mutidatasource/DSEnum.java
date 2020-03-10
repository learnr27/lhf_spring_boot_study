package com.lhf.springboot.common.mutidatasource;

/**
 * @ClassName: DSEnum
 * @Author: liuhefei
 * @Description: 多数据源的枚举
 * @Date: 2019/7/31 17:36
 */
public interface DSEnum {

    String DATA_SOURCE_CORE = "dataSourceCore";  //核心数据源

    String DATA_SOURCE_BIZ = "dataSourceBiz";  //其他业务的数据源
}
