package com.lhf.springboot.test;

import com.lhf.springboot.util.IdGenerator;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: IdGeneratorTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/16 18:10
 */
public class IdGeneratorTest {
    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorTest.class);


    public static void testBatchId() {
        for (int i = 0; i < 100; i++) {
            String batchId = IdGenerator.batchId(1001, 100);
            logger.info("批次号: "+ batchId);
        }
    }

    public static void testSimpleUUID() {
        for (int i = 0; i < 100; i++) {
            String simpleUUID = IdGenerator.simpleUUID();
            logger.info("simpleUUID: "+ simpleUUID);
        }
    }


    public static void testRandomUUID() {
        for (int i = 0; i < 100; i++) {
            String randomUUID = IdGenerator.randomUUID();
            logger.info("randomUUID: " + randomUUID);
        }
    }


    public static void testObjectID() {
        for (int i = 0; i < 100; i++) {
            String objectId = IdGenerator.objectId();
            logger.info("objectId: " + objectId);
        }
    }


    public static void testSnowflakeId() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 200; i++) {
            executorService.execute(() -> {
                logger.info("分布式 ID: " + IdGenerator.snowflakeId());
            });
        }
        executorService.shutdown();
    }

    public static void main(String[] args) {
        //testBatchId();
        //testSimpleUUID();
        //testRandomUUID();
        //testObjectID();
        testSnowflakeId();
    }
}
