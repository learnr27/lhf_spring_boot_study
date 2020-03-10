package com.lhf.springboot.service;

import com.lhf.springboot.pojo.UserInfo;

/**
 * @ClassName: UserInfoService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 11:08
 */
public interface UserInfoService {
    String buildUserInfo(UserInfo userInfo);

    void logout(String jwt);
}
