package com.lhf.springboot.dao;

import com.lhf.springboot.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ClassName: UserDao
 * @Author: liuhefei
 * @Description: 自定义用户查询接口
 * @Date: 2019/8/2 17:54
 */
public interface UserDao extends ElasticsearchRepository<User, Long> {

    /**
     * 自定义接口，可以进行定义查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findUserByName(String name, Pageable pageable);

    List<User> findUserByAddressEquals(String address);

    Page<User> findUserByPhone(String phone, Pageable pageable);

    List<User> findUserByNameOrPhone(String name, String phone);
}
