package com.lhf.springboot.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;

/**
 * @ClassName: IdGenerator
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/16 17:51
 */
public class IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);
    private static long workerId = 0;


    @PostConstruct
    void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            logger.info("当前机器 workerId: {}" + workerId);
        } catch (Exception e) {
            logger.warn("获取机器 ID 失败", e);
            workerId = NetUtil.getLocalhost().hashCode();
            logger.info("当前机器 workerId: {}" + workerId);
        }
    }

    /**
     * 获取一个批次号，形如 2019071015301361000101237
     *

     * 数据库使用 char(25) 存储
     *
     * @param tenantId 租户ID，5 位
     * @param module 业务模块ID，2 位
     * @return 返回批次号
     */
    public static synchronized String batchId(int tenantId, int module) {
        String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_MS_PATTERN);
        return prefix + tenantId + module + RandomUtil.randomNumbers(3);
    }

    @Deprecated
    public synchronized String getBatchId(int tenantId, int module) {
        return batchId(tenantId, module);
    }

    /**
     * 生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
     *
     * @return
     */
    public static String simpleUUID() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
     *
     * @return
     */
    public static String randomUUID() {
        return IdUtil.randomUUID();
    }

    private static Snowflake snowflake = IdUtil.createSnowflake(workerId, 1);

    public static synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long dataCenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        return snowflake.nextId();
    }

    /**
     * 生成类似：5b9e306a4df4f8c54a39fb0c
     *

     * ObjectId 是 MongoDB 数据库的一种唯一 ID 生成策略，
     * 是 UUID version1 的变种，详细介绍可见：服务化框架－分布式 Unique ID 的生成方法一览。
     *
     * @return
     */
    public static String objectId() {
        return ObjectId.next();
    }
}
