package com.lhf.springboot.service.impl;

import com.lhf.springboot.entity.Book;
import com.lhf.springboot.entity.BookVO;
import com.lhf.springboot.repository.BookCaseRepository;
import com.lhf.springboot.repository.BookRepository;
import com.lhf.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: BookServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:58
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private Integer limit = 10;

    @Override
    public BookVO findByPage(Integer page) {
        Integer index = (page - 1) * limit;
        BookVO bookVO = new BookVO();
        bookVO.setData(bookRepository.find(index, limit));
        bookVO.setTotal(bookRepository.count());
        bookVO.setPageSize(limit);
        return bookVO;
    }

    @Override
    public Integer save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Integer deleteById(Integer id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public Integer update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public Book findByIdAndName(Integer id, String name) {
        return bookRepository.findByIdAndName(id, name);
    }
}
