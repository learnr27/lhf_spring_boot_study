package com.lhf.springboot.service;

import com.lhf.springboot.pojo.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;

/**
 * @ClassName: BookService
 * @Author: liuhefei
 * @Description: 图书接口
 * @Date: 2019/8/2 18:13
 */
public interface BookService {

    /**
     * 新增图书信息
     *
     * @param book
     * @return
     */
    boolean insert(Book book);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<Book> searchBook(String searchContent);


    /**
     * 根据图书名字进行分页查询
     * @param pageNumber
     * @param pageSize
     * @param bookName
     * @return
     */
    List<Book> searchBookByBookName(Integer pageNumber, Integer pageSize, String bookName);


    Page<Book> findBookByBookNameNot(String name, Pageable page);

    Page<Book> findBookByPriceBetween(Integer price,Pageable page);

    Page<Book> findBookByBookNameLike(String name,Pageable page);

    Page<Book> findByMessage(String message, Pageable pageable);

    /**
     * 根据图书名和作者进行查询
     * @param author
     * @return
     */
    List<Book> searchBookByBookNameAndAuthor(String bookName, String author);

    /**
     * 根据图书名或作者进行查询
     * @param bookName
     * @param author
     * @return
     */
    List<Book> findBookByBookNameOrAuthor(String bookName, String author);

    /**
     * 根据图书名或价格进行查询
     * @param name
     * @param price
     * @return
     */
    List<Book> findBookByBookNameOrPrice(String name, Integer price);

    /**
     * 根据关键字进行搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<Book> searchBook(Integer pageNumber, Integer pageSize, String searchContent);

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<Book> searchBookByWeight(String searchContent);
}
