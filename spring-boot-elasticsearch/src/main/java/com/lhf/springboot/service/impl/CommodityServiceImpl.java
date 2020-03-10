package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.CommodityRepository;
import com.lhf.springboot.entity.Commodity;
import com.lhf.springboot.service.CommodityService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CommodityServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 9:50
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public long count() {
        return commodityRepository.count();
    }

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    @Override
    public void delete(Commodity commodity) {
        commodityRepository.delete(commodity);
    }

    @Override
    public Iterable<Commodity> getAll() {
        return commodityRepository.findAll();
    }

    @Override
    public List<Commodity> getByName(String name) {
        List<Commodity> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder=new MatchQueryBuilder("name", name);
        Iterable<Commodity> iterable = commodityRepository.search(matchQueryBuilder);
        iterable.forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery =new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return commodityRepository.search(searchQuery);
    }
}
