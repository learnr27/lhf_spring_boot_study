package com.lhf.springboot.service.impl;

import com.google.common.collect.Lists;
import com.lhf.springboot.dao.UserDao;
import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: liuhefei
 * @Description: 用户操作实现类
 * @Date: 2019/8/2 18:07
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public boolean insert(User user) {
        boolean flag = false;
        try{
            userDao.save(user);
            flag = true;
            logger.info("添加用户信息成功：{}", user);
        }catch (Exception e){
            logger.error("添加用户信息失败，错误信息：{}", e.getMessage());
        }
        return flag;
    }

    @Override
    public List<User> search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句：" + builder);
        Iterable<User> users = userDao.search(builder);
        //等价于下面这个方法
        List<User> userList = Lists.newArrayList(users);
       /* Iterator<User> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
       	   	list.add(iterator.next());
        }*/
        logger.info("根据关键字进行全文搜索用户：{}", userList);
        return userList;
    }

    @Override
    public List<User> searchByName(Integer pageNumber, Integer pageSize, String name) {
        Page<User> searchPageResults = userDao.findUserByName(name, PageRequest.of(pageNumber, pageSize));
        logger.info("根据用户名搜索结果：{}", searchPageResults);
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
        //分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句：" + searchQuery.getQuery().toString());

        Page<User> searchPageResults = userDao.search(searchQuery);

        logger.info("根据关键字进行用户搜索并分页：{}", searchPageResults);
        return searchPageResults.getContent();
    }

    @Override
    public List<User> searchUserByWeight(String searchContent) {
        //根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchPhraseQuery("name", searchContent));
        System.out.println("查询的语句：" + functionScoreQueryBuilder.toString());
        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
        List<User> list = Lists.newArrayList(searchResult);
        logger.info("根据权重进行用户查询：{}", list);
        return list;
    }

    @Override
    public List<User> findUserByAddressEquals(String address) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(address);
        System.out.println("查询的语句：" + builder);

        Iterable<User> users = userDao.search(builder);

        List<User> userList = Lists.newArrayList(users);

        logger.info("根据用户住址进行全文搜索用户：{}", userList);
        return userList;
    }

    @Override
    public Page<User> findUserByPhone(String phone, Pageable pageable) {
        Page<User> userPage = userDao.findUserByPhone(phone, pageable);
        logger.info("根据用户手机号进行分页查询：{}" , userPage);
        return userPage;
    }

    @Override
    public List<User> findUserByNameOrPhone(String name, String phone) {
        List<User> userList = userDao.findUserByNameOrPhone(name, phone);
        logger.info("根据用户名或手机号进行查询：{}", userList);
        return userList;
    }
}
