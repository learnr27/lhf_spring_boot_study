package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Commodity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ElasticsearchTemplateController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 15:11
 */
@Api("使用ElasticsearchTemplate方式操作Elasticsearch")
@RequestMapping("/es")
@RestController
public class ElasticsearchTemplateController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @ApiOperation("添加商品")
    @PostMapping("/add")
    public String addCommodity(@RequestBody Commodity commodity){
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(commodity).build();
        String num = elasticsearchTemplate.index(indexQuery);
        return num;
    }

    @GetMapping("/get")
    public List<Commodity> findByName(@RequestParam(value = "name", required = true) String name) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name))
                .build();
        List<Commodity> list = elasticsearchTemplate.queryForList(searchQuery, Commodity.class);
        System.out.println(list);
        return list;
    }

}
