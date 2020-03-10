package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Book;
import com.lhf.springboot.entity.BookVO;
import com.lhf.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: BookController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 15:05
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/findByPage/{page}")
    public BookVO findByPage(@PathVariable("page")Integer page){
        return bookService.findByPage(page);
    }

    @PostMapping("/save")
    public Integer save(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable("id")Integer id){
        return bookService.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id")Integer id){
        return bookService.findById(id);
    }

    @PutMapping("/update")
    public Integer update(@RequestBody Book book){
        return bookService.update(book);
    }
}
