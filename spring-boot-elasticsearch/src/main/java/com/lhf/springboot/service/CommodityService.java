package com.lhf.springboot.service;

import com.lhf.springboot.entity.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName: CommodityService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 9:50
 */
public interface CommodityService {
    long count();

    //添加
    Commodity save(Commodity commodity);

    //删除
    void delete(Commodity commodity);

    //查询
    Iterable<Commodity> getAll();

    List<Commodity> getByName(String name);

    //分页
    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
