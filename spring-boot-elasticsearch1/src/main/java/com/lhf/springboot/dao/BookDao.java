package com.lhf.springboot.dao;

import com.lhf.springboot.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ClassName: BookDao
 * @Author: liuhefei
 * @Description: 自定义图书查询接口
 * @Date: 2019/8/2 17:54
 */
public interface BookDao extends ElasticsearchRepository<Book, String> {

    List<Book> findBookByBookNameAndAuthorEquals(String bookName, String author);

    List<Book> findBookByBookNameOrAuthor(String bookName, String author);

    List<Book> findBookByBookNameOrPrice(String name, Integer price);

    Page<Book> findBookByBookName(String name, Pageable page);

    Page<Book> findBookByBookNameNot(String name,Pageable page);

    Page<Book> findBookByPriceBetween(int price,Pageable page);

    Page<Book> findBookByBookNameLike(String name,Pageable page);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"message\" : \"?0\"}}}}")
    Page<Book> findByMessage(String message, Pageable pageable);
}
