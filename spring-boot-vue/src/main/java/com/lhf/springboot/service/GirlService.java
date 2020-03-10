package com.lhf.springboot.service;

import com.lhf.springboot.entity.Girl;
import com.lhf.springboot.entity.GirlVO;

/**
 * @ClassName: GirlService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:17
 */
public interface GirlService {

    public GirlVO findByPage(Integer page);

    public Integer save(Girl girl);

    public Integer deleteById(Integer id);

    public Girl findById(Integer id);

    public Integer update(Girl girl);

    public Girl findByIdAndName(Integer id, String name);
}
