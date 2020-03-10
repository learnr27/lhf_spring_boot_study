package com.lhf.springboot.service;

import com.lhf.springboot.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 16:59
 */
public interface UserService {

    public List<User> getUserList();

    public User findUserById(Integer id);

    public void save(User user);

    public void edit(User user);

    @Transactional
    public void deleteUser(Integer id);
}
