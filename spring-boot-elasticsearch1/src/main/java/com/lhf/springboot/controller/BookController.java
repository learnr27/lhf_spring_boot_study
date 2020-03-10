package com.lhf.springboot.controller;

import com.lhf.springboot.pojo.Book;
import com.lhf.springboot.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BookController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 20:17
 */
@Api(value = "图书ES Api接口", tags = "图书")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("添加图书")
    @PostMapping("/addBook")
    public boolean createBook(@RequestBody Book book) {
        return bookService.insert(book);
    }


    @ApiOperation("根据关键字进行全文搜索图书")
    @GetMapping("/book/searchContent")
    public List<Book> search(@RequestParam(value = "searchContent") String searchContent) {
        return bookService.searchBook(searchContent);
    }

    @ApiOperation("根据关键字进行搜索图书并分页")
    @GetMapping("/pageBook")
    public List<Book> searchBook(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return bookService.searchBook(pageNumber, pageSize, searchContent);
    }


    @ApiOperation("根据关键词权重进行查询图书")
    @GetMapping("/bookWeight")
    public List<Book> searchBookByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return bookService.searchBookByWeight(searchContent);
    }


    @ApiOperation("根据图书名分页查询")
    @GetMapping("/findBookByName")
    public List<Book> searchBookByBookName(@RequestParam(value = "pageNumber") Integer pageNumber,
                                           @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                           @RequestParam(value = "bookName") String bookName){
        return bookService.searchBookByBookName(pageNumber, pageSize, bookName);
    }

    @ApiOperation("根据图书名分页查询")
    @GetMapping("/findBookByName1")
    public Page<Book> findBookByBookNameNot(@RequestParam(value = "name", required = true) String name,  @RequestParam(value = "page") Pageable page){
        return bookService.findBookByBookNameNot(name, page);
    }

    @ApiOperation("根据图书价格分页模糊查询")
    @GetMapping("/findBookPrice")
    public Page<Book> findBookByPriceBetween(@RequestParam(value = "price", required = true) Integer price, @RequestParam(value = "page")Pageable page){
       return bookService.findBookByPriceBetween(price, page);
    }

    @ApiOperation("根据图书名分页模糊查询")
    @GetMapping("/findBookByName2")
    public Page<Book> findBookByBookNameLike(@RequestParam(value = "name", required = true)String name,@RequestParam(value = "page")Pageable page){
        return bookService.findBookByBookNameLike(name, page);
    }

    @ApiOperation("信息查询")
    @GetMapping("/findByMessage")
    public Page<Book> findByMessage(@RequestParam(value = "message", required = true)String message, @RequestParam(value = "pageable")Pageable pageable){
        return bookService.findByMessage(message, pageable);
    }


    @ApiOperation("根据图书名和作者进行查询")
    @GetMapping(value = "/findByNameAndAuthor")
    public List<Book> searchBookByBookNameAndAuthor(@RequestParam(value = "bookName", required = true) String bookName, @RequestParam(value = "author", required = true) String author){
        return bookService.searchBookByBookNameAndAuthor(bookName, author);
    }

    @ApiOperation("根据图书名或作者进行查询")
    @GetMapping(value = "/findByNameOrAuthor")
    public List<Book> findBookByBookNameOrAuthor(@RequestParam(value = "bookName")String bookName, @RequestParam(value = "author")String author){
        return bookService.findBookByBookNameOrAuthor(bookName, author);
    }


    @ApiOperation("根据图书名或价格进行查询")
    @GetMapping(value = "/findByNameOrPrice")
    public List<Book> findBookByBookNameOrPrice(@RequestParam(value = "name")String name, @RequestParam(value = "price")Integer price){
       return bookService.findBookByBookNameOrPrice(name, price);
    }



}
