package com.lhf.springboot.service.impl;

import com.google.common.collect.Lists;
import com.lhf.springboot.dao.BookDao;
import com.lhf.springboot.pojo.Book;
import com.lhf.springboot.service.BookService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BookServiceImpl
 * @Author: liuhefei
 * @Description: 图书操作实现类
 * @Date: 2019/8/2 18:16
 */
@Service
public class BookServiceImpl implements BookService {

    private final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean insert(Book book) {
        boolean flag = false;
        try{
            bookDao.save(book);
            flag = true;
            logger.info("添加图书信息成功：{}", book);
        }catch (Exception e){
            logger.error("添加图书信息失败，错误信息：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Book> searchBook(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句：" + builder);
        Iterable<Book> books = bookDao.search(builder);
        //等价于下面这个方法
        List<Book> bookList = Lists.newArrayList(books);
       /* Iterator<User> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
       	   	list.add(iterator.next());
        }*/
        logger.info("根据关键字进行全文搜索图书：{}", bookList);
        return bookList;
    }

    @Override
    public List<Book> searchBookByBookName(Integer pageNumber, Integer pageSize, String bookname) {
        /*Page<Book> searchPageResults = bookDao.findBookByBookName(bookname, PageRequest.of(pageNumber, pageSize));
        logger.info("根据图书名搜索结果：{}", searchPageResults);
        return searchPageResults.getContent();*/

        //分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(bookname);

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句：" + searchQuery.getQuery().toString());

        Page<Book> searchPageResults = bookDao.search(searchQuery);

        logger.info("根据关键字进行用户搜索并分页：{}", searchPageResults);
        return searchPageResults.getContent();
    }

    @Override
    public Page<Book> findBookByBookNameNot(String bookname, Pageable page) {
        /*Page<Book> searchPageResults = bookDao.findBookByBookNameNot(bookname, page);
        logger.info("根据图书名进行分页查询结果：{}", searchPageResults);
        return (Page<Book>) searchPageResults.getContent();*/

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(bookname);

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(page).withQuery(builder).build();
        System.out.println("查询的语句：" + searchQuery.getQuery().toString());

        Page<Book> searchPageResults = bookDao.search(searchQuery);

        logger.info("根据关键字进行用户搜索并分页：{}", searchPageResults);
        return (Page<Book>) searchPageResults.getContent();
    }

    @Override
    public Page<Book> findBookByPriceBetween(Integer price, Pageable page) {
        Page<Book> searchPageResults = bookDao.findBookByPriceBetween(price, page);
        logger.info("根据图书价格进行分页查询结果：{}", searchPageResults);
        return (Page<Book>) searchPageResults.getContent();
    }

    @Override
    public Page<Book> findBookByBookNameLike(String name, Pageable page) {
        Page<Book> searchPageResults = bookDao.findBookByBookNameLike(name, page);
        logger.info("根据图书名进行分页查询结果：{}", searchPageResults);
        return (Page<Book>) searchPageResults.getContent();
    }

    @Override
    public Page<Book> findByMessage(String message, Pageable pageable) {
        Page<Book> searchPageResults = bookDao.findByMessage(message, pageable);
        logger.info("根据信息进行分页查询结果：{}", searchPageResults);
        return (Page<Book>) searchPageResults.getContent();
    }

    @Override
    public List<Book> searchBookByBookNameAndAuthor( String bookName, String author) {
        List<Book> bookList = bookDao.findBookByBookNameAndAuthorEquals(bookName, author);
        logger.info("根据图书名和作者搜索结果：{}", bookList);
        return bookList;
    }

    @Override
    public List<Book> findBookByBookNameOrAuthor( String bookName, String author) {
        List<Book> bookList = bookDao.findBookByBookNameOrAuthor(bookName, author);
        logger.info("根据图书名或作者搜索结果：{}", bookList);
        return bookList;
    }

    @Override
    public List<Book> findBookByBookNameOrPrice( String bookName, Integer price) {
        List<Book> bookList = bookDao.findBookByBookNameOrPrice(bookName, price);
        logger.info("根据图书名或价格搜索结果：{}", bookList);
        return bookList;
    }

    @Override
    public List<Book> searchBook(Integer pageNumber, Integer pageSize, String searchContent) {
        //分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句：" + searchQuery.getQuery().toString());
        Page<Book> searchPageResults = bookDao.search(searchQuery);
        logger.info("根据关键字进行图书搜索并分页：{}", searchPageResults);
        return searchPageResults.getContent();
    }

    @Override
    public List<Book> searchBookByWeight(String searchContent) {
        //根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("bookName", searchContent));
        System.out.println("查询的语句：" + functionScoreQueryBuilder.toString());
        Iterable<Book> searchResult = bookDao.search(functionScoreQueryBuilder);
        List<Book> bookList = Lists.newArrayList(searchResult);
        logger.info("根据权重进行图书查询：{}", bookList);
        return bookList;
    }
}
