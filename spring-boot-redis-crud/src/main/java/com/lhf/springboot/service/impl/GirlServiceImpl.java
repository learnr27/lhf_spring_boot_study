package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.GirlDao;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: GirlServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/25 17:07
 */
@Service
public class GirlServiceImpl implements GirlService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GirlDao girlDao;


    @Override
    public boolean addGirl(Girl girl) {
        boolean flag = false;
        try{
            girlDao.addGirl(girl);
            flag = true;
        }catch (Exception e){
            logger.error("添加失败！" , e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean updateGirl(Girl girl) {
        boolean flag =false;
        try{
            girlDao.updateGirl(girl);
            flag = true;
        }catch (Exception e){
            logger.error("修改失败！", e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean deleteGirl(int id) {
        boolean flag = false;
        try{
            girlDao.deleteGirl(id);
            flag = true;
        }catch (Exception e){
            logger.error("删除失败！", e.getMessage());
        }
        return flag;
    }

    @Override
    public Girl findById(int id) {
        return girlDao.findById(id);
    }

    @Override
    public List<Girl> findByGirlList() {
        return girlDao.findByGirlList();
    }
}
