package com.lhf.springboot.dao.impl;

import com.alibaba.fastjson.JSON;
import com.lhf.springboot.dao.GirlDao;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.util.RedisUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: GirlDaoImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/25 16:40
 */
@Repository
public class GirlDaoImpl implements GirlDao {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void addGirl(Girl girl) {
        redisUtil.set(String.valueOf(girl.getId()), girl.toString());
    }

    @Override
    public void updateGirl(Girl girl) {
       redisUtil.set(String.valueOf(girl.getId()), girl.toString());
    }

    @Override
    public void deleteGirl(int id) {
        redisUtil.del(String.valueOf(id));
    }

    @Override
    public Girl findById(int id) {
        String data = redisUtil.get(String.valueOf(id)).toString();
        Girl girl = JSON.parseObject(data, Girl.class);
        return girl;
    }

    @Override
    public List<Girl> findByGirlList() {
        List<Girl> girlList = new ArrayList<>();
        String data = null;
        Set<String> keysList = redisUtil.getKeys();
        for(String key : keysList){
            System.out.println("key = " + key);
            data = (String) redisUtil.get(key);
            Girl girl = JSON.parseObject(data, Girl.class);
            System.out.println("girl = " + girl);
            girlList.add(girl);
        }

        return girlList;
    }
}
