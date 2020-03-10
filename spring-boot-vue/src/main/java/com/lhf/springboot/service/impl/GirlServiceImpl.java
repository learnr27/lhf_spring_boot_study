package com.lhf.springboot.service.impl;

import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.entity.GirlVO;
import com.lhf.springboot.repository.GirlRepository;
import com.lhf.springboot.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: GirlServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:17
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlRepository girlRepository;

    private Integer limit = 10;

    @Override
    public GirlVO findByPage(Integer page) {
        Integer index = (page - 1) * limit;
        GirlVO girlVO = new GirlVO();
        girlVO.setData(girlRepository.find(index, limit));
        girlVO.setPageSize(limit);
        girlVO.setTotal(girlRepository.count());
        return girlVO;
    }

    @Override
    public Integer save(Girl girl) {
        return girlRepository.save(girl);
    }

    @Override
    public Integer deleteById(Integer id) {
        return girlRepository.deleteById(id);
    }

    @Override
    public Girl findById(Integer id) {
        return girlRepository.findById(id);
    }

    @Override
    public Integer update(Girl girl) {
        return girlRepository.update(girl);
    }

    @Override
    public Girl findByIdAndName(Integer id, String name) {
        return girlRepository.findByIdAndName(id, name);
    }
}
