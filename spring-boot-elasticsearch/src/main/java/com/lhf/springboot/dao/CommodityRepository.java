package com.lhf.springboot.dao;

import com.lhf.springboot.entity.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: CommodityRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 9:50
 */
@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {
}
