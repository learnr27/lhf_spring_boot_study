package com.lhf.springboot.service;

import com.lhf.springboot.model.Aa;
import com.lhf.springboot.repository.AaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/24 15:57
 */
@Service
public class AaServiceImpl implements AaService {

    @Autowired(required=true)
    private AaRepository aRepository;

    @Override
    public void save(Aa aa) {
        aRepository.save(aa);
    }
}
