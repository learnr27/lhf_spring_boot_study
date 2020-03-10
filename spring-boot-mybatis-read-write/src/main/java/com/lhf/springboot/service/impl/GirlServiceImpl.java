package com.lhf.springboot.service.impl;

import com.lhf.springboot.annotation.Master;
import com.lhf.springboot.annotation.Slave;
import com.lhf.springboot.config.DynamicDataSource;
import com.lhf.springboot.model.Girl;
import com.lhf.springboot.model.mapper.GirlMapper;
import com.lhf.springboot.service.GirlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: GirlServiceImpl
 * @Author: liuhefei
 * @Description: 女孩相关业务接口实现
 * @Date: 2019/8/28 12:28
 */
@Slf4j
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlMapper girlMapper;

    @Slave
    @Override
    public List<Girl> selectAll() {
        return girlMapper.selectAll();
    }

    @Master
    @Override
    public boolean addGirl(Girl girl) {
        return girlMapper.insertSelective(girl) > 0;
    }

    @Master
    @Override
    public boolean updateGirl(Girl girl) {
        return girlMapper.updateByPrimaryKey(girl) > 0;
    }

    @Master
    @Override
    public boolean deleteById(int id) {
        return girlMapper.deleteByPrimaryKey(id)>0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertAndUpdate(Girl girl) {
        log.info("当前key: " + DynamicDataSource.getDatabaseType().name());
        int count = 0;
        count += girlMapper.insertSelective(girl);
        girl = null;
        girl.getId();
        count += girlMapper.updateByPrimaryKey(girl);

        return count > 1;
    }
}
