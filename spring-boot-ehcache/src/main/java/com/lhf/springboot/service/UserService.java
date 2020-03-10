package com.lhf.springboot.service;

import com.lhf.springboot.model.UserInfo;
import org.springframework.cache.annotation.Cacheable;

/**
 * @ClassName: UserService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/11 16:04
 */
public interface UserService {

    UserInfo findUserById(Long id);

    UserInfo findUserById1(Long id);


    UserInfo findUserById2(Long id);


    UserInfo findUserById3(Long id);


    UserInfo findUserById4(Long id);

}
