package com.lhf.springboot.service;

import com.lhf.springboot.pojo.UserInfo;
import com.lhf.springboot.utils.JWTUtil;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UserInfoServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 11:08
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String buildUserInfo(UserInfo userInfo) {
        String username = userInfo.getUsername();
        String jwt = JWTUtil.sign(username, JWTUtil.SECRET);
        Assert.notNull(jwt, "jwt cannot null");
        RBucket rBucket = redissonClient.getBucket(jwt);
        rBucket.set(userInfo, JWTUtil.EXPIRE_TIME_MS, TimeUnit.MILLISECONDS);
        return jwt;
    }

    @Override
    public void logout(String jwt){
        RBucket rBucket = redissonClient.getBucket(jwt);
        rBucket.delete();
    }

}
