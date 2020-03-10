package com.lhf.springboot.repository;

import com.lhf.springboot.model.User;

/**
 * @ClassName: UserRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/30 9:34
 */
public interface UserRepository {
    public void saveUser(User user);

    public User findUserByName(String name);

    public long updateUser(User user);

    public void deleteUserById(int id);
}
