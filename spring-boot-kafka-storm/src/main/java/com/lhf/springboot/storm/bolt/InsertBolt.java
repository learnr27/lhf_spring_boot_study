package com.lhf.springboot.storm.bolt;

import com.alibaba.fastjson.JSON;
import com.lhf.springboot.constant.Constants;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.service.GirlService;
import com.lhf.springboot.util.GetSpringBean;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: InsertBolt
 * @Author: liuhefei
 * @Description: 写入数据的bolt
 * @Date: 2019/11/6 17:12
 */
public class InsertBolt extends BaseRichBolt {


    private static final long serialVersionUID = 4429781625696921930L;

    private static final Logger logger = LoggerFactory.getLogger(InsertBolt.class);

    private GirlService girlService;

    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map<String, Object> map, TopologyContext topologyContext, OutputCollector outputCollector) {
        girlService = GetSpringBean.getBean(GirlService.class);
    }

    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField(Constants.FIELD);
        try{
            List<Girl> girlList = JSON.parseArray(msg, Girl.class);
            //移除age小于18的数据
            if(girlList != null && girlList.size() > 0){
                Iterator<Girl> iterator = girlList.iterator();
                while (iterator.hasNext()){
                    Girl girl = iterator.next();
                    if(girl.getAge() < 18){
                        logger.warn("Bolt移除的数据：{}", girl);
                        iterator.remove();
                    }
                }
                if(girlList != null && girlList.size() > 0){
                    girlService.insertBatch(girlList);
                }
            }
        }catch (Exception e){
            logger.error("Bolt的数据处理失败！数据：{}", msg, e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    /**
     * cleanup是IBolt接口中定义,用于释放bolt占用的资源。
     * Storm在终止一个bolt之前会调用这个方法。
     */
    @Override
    public void cleanup(){

    }
}
