package com.lhf.springboot.repository;

import com.lhf.springboot.entity.Book;

import java.util.List;

/**
 * @ClassName: BookRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:56
 */
public interface BookRepository {

    public List<Book> find(Integer index,Integer limit);
    public Integer count();
    public Integer save(Book book);
    public Integer deleteById(Integer id);
    public Book findById(Integer id);
    public Integer update(Book book);
    public Book findByIdAndName(Integer id, String name);
}
