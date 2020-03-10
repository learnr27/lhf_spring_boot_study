package com.lhf.springboot.service.impl;

import com.lhf.springboot.annotation.Master;
import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.mapper.GirlMapper;
import com.lhf.springboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: GirlServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/28 16:56
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired(required=false)
    private GirlMapper girlMapper;

    @Override
    public Girl findByGrilId(int id){
        return girlMapper.findByGrilId(id);
    }


    @Override
    public List<Girl> selectAll() {
        return girlMapper.selectAll();
    }

    @Master
    @Transactional
    @Override
    public boolean addGirl(Girl girl) {
        System.out.println(girl);
        return girlMapper.addGirl(girl);
    }

    @Master
    @Override
    public boolean updateGirl(Girl girl) {
        return girlMapper.updateGirl(girl);
    }

    @Master
    @Override
    public boolean deleteById(int id) {
        return girlMapper.deleteById(id);
    }
}
