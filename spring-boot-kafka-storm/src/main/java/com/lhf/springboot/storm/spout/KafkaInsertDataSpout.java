package com.lhf.springboot.storm.spout;

import com.alibaba.fastjson.JSON;
import com.lhf.springboot.config.ApplicationConfiguration;
import com.lhf.springboot.constant.Constants;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.util.GetSpringBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: KafkaInsertDataSpout
 * @Author: liuhefei
 * @Description: 从kafka获取新增数据
 * @Date: 2019/11/6 17:24
 */
public class KafkaInsertDataSpout extends BaseRichSpout {
    private static final long serialVersionUID = 3175278388014907269L;

    private static final Logger logger = LoggerFactory.getLogger(KafkaInsertDataSpout.class);

    private SpoutOutputCollector collector;

    private KafkaConsumer<String, String> consumer;

    private ConsumerRecords<String, String> msgList;

    private ApplicationConfiguration app;

    @SuppressWarnings("rawtypes")
    @Override
    public void open(Map<String, Object> map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        app = GetSpringBean.getBean(ApplicationConfiguration.class);
        kafkaInit();
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        for( ; ; ){
            try{
                msgList = consumer.poll(100);
                if(null != msgList && !msgList.isEmpty()){
                    String msg = "";
                    List<Girl> list = new ArrayList<>();
                    for(ConsumerRecord<String, String> record : msgList){
                        //原始数据
                        msg = record.value();
                        if(null == msg || "".equals(msg.trim())){
                            continue;
                        }
                        try{
                            list.add(JSON.parseObject(msg, Girl.class));
                        }catch (Exception e){
                            logger.error("数据格式有误！数据：{}", msg);
                            continue;
                        }
                    }
                    logger.info("Spout发送的数据： " + list);
                    //发送到bolt中
                    this.collector.emit(new Values(JSON.toJSONString(list)));
                    consumer.commitAsync();
                }else {
                    TimeUnit.SECONDS.sleep(3);
                    logger.info("未拉取到数据！");
                }
            }catch (Exception e){
                logger.error("消息队列处理异常！", e);
                try{
                    TimeUnit.SECONDS.sleep(10);
                }catch (InterruptedException e1){
                    logger.error("暂停失败！", e1);
                }
            }
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constants.FIELD));

    }

    /**
     * 初始化kafka配置
     */
    private void kafkaInit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", app.getServers());
        props.put("max.poll.records", app.getMaxPollRecords());
        props.put("enable.auto.commit", app.getAutoCommit());
        props.put("group.id", app.getGroupId());
        props.put("auto.offset.reset", app.getCommitRule());
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        String topic=app.getTopicName();
        this.consumer.subscribe(Arrays.asList(topic));
        logger.info("消息队列[" + topic + "] 开始初始化...");
    }
}
