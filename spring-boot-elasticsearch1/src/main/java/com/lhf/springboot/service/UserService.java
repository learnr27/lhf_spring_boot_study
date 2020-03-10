package com.lhf.springboot.service;

import com.lhf.springboot.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author: liuhefei
 * @Description: 用户接口
 * @Date: 2019/8/2 18:06
 */
public interface UserService {

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 根据关键字进行全文搜索
     * @param searchContent
     * @return
     */
    List<User> search(String searchContent);


    /**
     * 根据名字进行分页查询
     * @param pageNumber
     * @param pageSize
     * @param name
     * @return
     */
    List<User> searchByName(Integer pageNumber, Integer pageSize,String name);

    /**
     * 根据关键字进行搜索并分页
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent);

    /**
     * 根据关键词权重进行查询
     * @param searchContent
     * @return
     */
    List<User> searchUserByWeight(String searchContent);

    /**
     * 根据用户住址进行查询
     * @param address
     * @return
     */
    List<User> findUserByAddressEquals(String address);

    /**
     * 根据用户手机号码进行分页查询
     * @param phone
     * @param pageable
     * @return
     */
    Page<User> findUserByPhone(String phone, Pageable pageable);

    /**
     * 根据用户名或手机号进行查询
     * @param name
     * @param phone
     * @return
     */
    List<User> findUserByNameOrPhone(String name, String phone);


}
