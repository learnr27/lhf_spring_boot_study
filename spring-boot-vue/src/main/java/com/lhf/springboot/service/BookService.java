package com.lhf.springboot.service;

import com.lhf.springboot.entity.Book;
import com.lhf.springboot.entity.BookVO;

/**
 * @ClassName: BookService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:57
 */
public interface BookService {

    public BookVO findByPage(Integer page);

    public Integer save(Book book);

    public Integer deleteById(Integer id);

    public Book findById(Integer id);

    public Integer update(Book book);

    public Book findByIdAndName(Integer id, String name);
}
