package com.lhf.springboot.repository;

import com.lhf.springboot.entity.Girl;

import java.util.List;

/**
 * @ClassName: GirlRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:16
 */
public interface GirlRepository {

    public List<Girl> find(Integer index, Integer limit);
    public Integer count();
    public Integer save(Girl girl);
    public Integer deleteById(Integer id);
    public Girl findById(Integer id);
    public Integer update(Girl girl);
    public Girl findByIdAndName(Integer id, String name);
}
