package com.lhf.springboot.service;

import com.lhf.springboot.model.UserInfo;
import com.lhf.springboot.util.RandomUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/11 16:06
 */

@Service
@Transactional     //事务，表示该类下所有的都受事务控制
public class UserServiceImpl implements UserService {
    @Override
    @Cacheable(cacheNames = "userInfo",key = "#id")
    public UserInfo findUserById(Long id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setNickName(RandomUtils.getRandomString(5));
        user.setAge(Integer.valueOf(RandomUtils.getRandomNum(2)));
        user.setPhone("182"+RandomUtils.getRandomNum(8));
        user.setEmail(RandomUtils.getRandomString(10)+"@163.com");
        System.out.println("user = " + user);
        return user;
    }

    @Override
    @Cacheable(value="userInfo", key="#p0")
    public UserInfo findUserById1(Long id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setNickName(RandomUtils.getRandomString(5));
        user.setAge(Integer.valueOf(RandomUtils.getRandomNum(2)));
        user.setPhone("182"+RandomUtils.getRandomNum(8));
        user.setEmail(RandomUtils.getRandomString(10)+"@163.com");
        System.out.println("user = " + user);
        return user;
    }

    @Override
    @Cacheable(value="userInfo", key="#user.id")
    public UserInfo findUserById2(Long id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setNickName(RandomUtils.getRandomString(5));
        user.setAge(Integer.valueOf(RandomUtils.getRandomNum(2)));
        user.setPhone("182"+RandomUtils.getRandomNum(8));
        user.setEmail(RandomUtils.getRandomString(10)+"@163.com");
        System.out.println("user = " + user);
        return user;
    }

    @Override
    @Cacheable(value="userInfo", key="#p0.id")
    public UserInfo findUserById3(Long id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setNickName(RandomUtils.getRandomString(5));
        user.setAge(Integer.valueOf(RandomUtils.getRandomNum(2)));
        user.setPhone("182"+RandomUtils.getRandomNum(8));
        user.setEmail(RandomUtils.getRandomString(10)+"@163.com");
        System.out.println("user = " + user);
        return user;
    }

    @Override
    @CacheEvict(value="userInfo",allEntries=true)   //清除缓存
    public UserInfo findUserById4(Long id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        user.setNickName(RandomUtils.getRandomString(5));
        user.setAge(Integer.valueOf(RandomUtils.getRandomNum(2)));
        user.setPhone("182"+RandomUtils.getRandomNum(8));
        user.setEmail(RandomUtils.getRandomString(10)+"@163.com");
        System.out.println("user = " + user);
        return user;
    }

}
