package com.lhf.springboot.storm.demo.test;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * @ClassName: TestBolt
 * @Author: liuhefei
 * @Description: 用于处理消息
 * @Date: 2019/11/6 17:44
 */
public class TestBolt extends BaseRichBolt {
    private static final long serialVersionUID = 444407462066384566L;

    private long count=1;


    /**
     * 在Bolt启动前执行，提供Bolt启动环境配置的入口
     * 一般对于不可序列化的对象进行实例化。
     * 注:如果是可以序列化的对象，那么最好是使用构造函数。
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
        System.out.println("prepare: " + map.get("test"));
    }

    /**
     * execute()方法是Bolt实现的核心。
     * 也就是执行方法，每次Bolt从流接收一个订阅的tuple，都会调用这个方法。
     */
    @Override
    public void execute(Tuple tuple) {
        /**
         * 接受消息可以使用这两种方式进行接收。
         * 个人推荐第二种。
         */
        //String msg=tuple.getString(0);
        String msg=tuple.getStringByField("test");
        //这里我们就不做消息的处理，只打印
        System.out.println("Bolt第"+count+"接受的消息:"+msg);
        count++;
        /**
         *
         * 没次调用处理一个输入的tuple，所有的tuple都必须在一定时间内应答。
         * 可以是ack或者fail。否则，spout就会重发tuple。
         * 如果继承的是IRichBolt，则需要手动ack。
         * 这里就不用了,BaseRichBolt会自动帮我们应答。
         */
         //collector.ack(tuple);
    }

    /**
     * 声明数据格式
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup() {
        System.out.println("资源释放");
    }
}
