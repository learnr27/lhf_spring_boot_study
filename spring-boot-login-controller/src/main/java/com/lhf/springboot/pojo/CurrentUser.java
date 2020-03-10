package com.lhf.springboot.pojo;

/**
 * @ClassName: CurrentUser
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 10:41
 */
public class CurrentUser {

    private static final ThreadLocal<UserInfo> currentUser = new ThreadLocal<>();

    public static void put(UserInfo userInfo){
        currentUser.set(userInfo);
    }

    public static UserInfo get(){
        return currentUser.get();
    }
}
