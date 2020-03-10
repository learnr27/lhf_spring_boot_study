package com.lhf.springboot.controller;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ESController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/2 19:14
 */
@RestController
public class ESController {

    @Autowired
    private TransportClient client;

    @GetMapping("/")
    public String index(){
        return "success";
    }

    /**
     * 根据id查询
     * http://localhost:8080/get/book/noval?id=2
     * @param id
     * @return
     */
    @GetMapping("/get/book/novel")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("book", "noval", id).get();

        //如果不存在
        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }

    /**
     * 添加图书
     * @param title
     * @param author
     * @param wordCount
     * @param publishDate
     * @return
     */
    @PostMapping("/add/book/novel")
    @ResponseBody
    public ResponseEntity add(@RequestParam(name = "title")String title,
                              @RequestParam(name = "author")String author,
                              @RequestParam(name = "word_count")int wordCount,
                              @RequestParam(name = "publish_date")
                              @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date publishDate){
        try{
            XContentBuilder content = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("title", title)
                    .field("author", author)
                    .field("word_count", wordCount)
                    .field("publish_date", publishDate.getTime())
                    .endObject();
            IndexResponse result = this.client.prepareIndex("book", "novel")
                        .setSource(content)
                        .get();  //构建索引名
            return new ResponseEntity(result.getId(), HttpStatus.OK);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/book/novel")
    @ResponseBody
    public ResponseEntity delete(@RequestParam(name = "id")String id){
        DeleteResponse result = this.client.prepareDelete("book", "novel", id).get();

        return new ResponseEntity(((DeleteResponse) result).getResult().toString(), HttpStatus.OK);
    }

    @PutMapping("/update/book/novel")
    @ResponseBody
    public ResponseEntity update(@RequestParam(name = "id")String id,
                                 @RequestParam(name = "title", required = false)String title,
                                 @RequestParam(name = "author", required = false)String author,
                                 @RequestParam(name = "word_count", required = false)int wordCount,
                                 @RequestParam(name = "publish_date", required = false)
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date publishDate){
        UpdateRequest update = new UpdateRequest("book", "novel", id);
        try{
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject();
            if(title != null){
                builder.field("title", title);
            }
            if(author != null){
                builder.field("author", author);
            }
            if(wordCount != 0){
                builder.field("word_count", wordCount);
            }
            if(publishDate != null){
                builder.field("publish_date", publishDate);
            }
            builder.endArray();
            update.doc(builder);

            UpdateResponse result = this.client.update(update).get();
            return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 复合查询
     * @param author
     * @param title
     * @param gtWordCount  大于多少字数
     * @param ltWordCount  小于多少字数
     * @return
     */
    @PostMapping("/query/book/novel")
    @ResponseBody
    public ResponseEntity query(@RequestParam(name = "author", required = false)String author,
                                @RequestParam(name = "title", required = false)String title,
                                @RequestParam(name = "gt_word_count", defaultValue = "0")int gtWordCount,
                                @RequestParam(name = "lt_word_count", required = false)Integer ltWordCount){
        //构造复合查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(author != null){
            boolQuery.must(QueryBuilders.matchQuery("author", author));
        }
        if(title != null){
            boolQuery.must(QueryBuilders.matchQuery("title", title));
        }
        RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery("word_count").from(gtWordCount);
        if(ltWordCount != null && ltWordCount > 0){
            rangeBuilder.to(ltWordCount);
        }
        boolQuery.filter(rangeBuilder);

        SearchRequestBuilder builder = this.client.prepareSearch("book")
                .setTypes("novel")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setFrom(0)
                .setSize(10);
        System.out.println("builder = " + builder);
        SearchResponse response = builder.get();  //获取响应结果
        List<Map<String, Object>> result = new ArrayList<>();
        for(SearchHit hit : response.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }

}
