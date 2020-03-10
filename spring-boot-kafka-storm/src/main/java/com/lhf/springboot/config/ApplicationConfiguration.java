package com.lhf.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationConfiguration
 * @Author: liuhefei
 * @Description: 获取application文件的信息
 * @Date: 2019/11/6 15:27
 */
@Component("applicationConfiguration")
public class ApplicationConfiguration {

    @Value("${kafka.topicName}")
    private String topicName;

    @Value("${kafka.servers}")
    private String servers;


    @Value("${kafka.maxPollRecords}")
    private int maxPollRecords;

    @Value("${kafka.commitRule}")
    private String commitRule;

    @Value("${kafka.autoCommit}")
    private String autoCommit;


    @Value("${kafka.groupId}")
    private String groupId;
    /**
     * 获取topicName
     * @return  topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * 获取servers
     * @return  servers
     */
    public String getServers() {
        return servers;
    }

    /**
     * 获取maxPollRecords
     * @return  maxPollRecords
     */
    public int getMaxPollRecords() {
        return maxPollRecords;
    }

    /**
     * 获取commitRule
     * @return  commitRule
     */
    public String getCommitRule() {
        return commitRule;
    }



    /**
     * 获取autoCommit
     * @return  autoCommit
     */
    public String getAutoCommit() {
        return autoCommit;
    }

    /**
     * 获取groupId
     * @return  groupId
     */
    public String getGroupId() {
        return groupId;
    }

}
