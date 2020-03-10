package com.lhf.springboot.controller;

import com.lhf.springboot.model.Book;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BookController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/10 15:08
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @RequestMapping(value = "/bookIndex")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("bookIndex");
        return mv;
    }

    @RequestMapping("/getBookAll")
    public Book getBookAll(@RequestBody Book book){
        book.setBookName("SpringBoot系列教程");
        book.setBookAuthor("霜花似雪");
        book.setBookPrice(78.88);
        book.setRemark("最全面的SpringBoot教程");
        return book;
    }

    @RequestMapping(value = "/bookList")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("bookList");
        return mv;
    }

    @RequestMapping("/getBookList")
    public List<Book> getBookList(){
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "SpringBoot系列教程", "霜花似雪", 88.5, "SpringBoot系列教程"));
        list.add(new Book(2, "Spring系列教程", "霜花似雪", 78.5, "Spring系列教程"));
        list.add(new Book(3, "SpringCloud系列教程", "霜花似雪", 68.5, "SpringCloud系列教程"));
        return list;

    }
}
