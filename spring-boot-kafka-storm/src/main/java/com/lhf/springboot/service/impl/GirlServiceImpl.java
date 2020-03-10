package com.lhf.springboot.service.impl;


import com.lhf.springboot.dao.GirlDao;
import com.lhf.springboot.pojo.Girl;
import com.lhf.springboot.pojo.GirlVO;
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
 * @Date: 2019/10/30 16:17
 */
@Service
public class GirlServiceImpl implements GirlService {

    private static final Logger logger = LoggerFactory.getLogger(GirlServiceImpl.class);

    @Autowired
    private GirlDao girlDao;

    private Integer limit = 10;

    @Override
    public boolean insertBatch(List<Girl> girlList) {
        boolean flag=false;
        try {
            flag=girlDao.insertBatch(girlList);
            logger.info("批量新增"+ girlList.size()+"条数据成功!");
        } catch (Exception e) {
            logger.error("批量新增失败!数据:{},原因是:",e);
        }
        return flag;
    }

    @Override
    public List<Girl> findByGirl(Girl girl) {
        return girlDao.findByGirl(girl);
    }

    @Override
    public GirlVO findByPage(Integer page) {
        Integer index = (page - 1) * limit;
        GirlVO girlVO = new GirlVO();
        girlVO.setData(girlDao.find(index, limit));
        girlVO.setPageSize(limit);
        girlVO.setTotal(girlDao.count());
        return girlVO;
    }

    @Override
    public Integer save(Girl girl) {
        return girlDao.save(girl);
    }

    @Override
    public Integer deleteById(Integer id) {
        return girlDao.deleteById(id);
    }

    @Override
    public Girl findById(Integer id) {
        return girlDao.findById(id);
    }

    @Override
    public Integer update(Girl girl) {
        return girlDao.update(girl);
    }

    @Override
    public Girl findByIdAndName(Integer id, String name) {
        return girlDao.findByIdAndName(id, name);
    }
}
