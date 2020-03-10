package com.lhf.springboot.mapper.test2;

import com.lhf.springboot.model.Users;

import java.util.List;

/**
 * @ClassName: User2Mapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/26 19:18
 */
public interface User2Mapper {

    List<Users> getAll();

    Users getOne(Long id);

    void insert(Users user);

    void update(Users user);

    void delete(Long id);
}
