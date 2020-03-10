package com.lhf.springboot.mapper;

import com.lhf.springboot.model.Users;

import java.util.List;

/**
 * @ClassName: UsersMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/4 16:54
 */
public interface UsersMapper {
    List<Users> getAll();

    Users getOne(Long id);

    void insert(Users users);

    void update(Users users);

    void delete(Long id);
}
