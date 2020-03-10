package com.lhf.springboot.service;


import com.lhf.springboot.common.annotion.DataSource;
import com.lhf.springboot.common.dao.entity.User;
import com.lhf.springboot.common.dao.repository.UserMapper;
import com.lhf.springboot.common.mutidatasource.DSEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName: UserService
 * @Author: liuhefei
 * @Description: 用户
 * @Date: 2019/7/31 17:48
 */
@Service
@Transactional
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    @DataSource(name = DSEnum.DATA_SOURCE_CORE)
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public User findById1(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 新增用户
     * @param user
     */
    public Integer insertUser(User user) {
        return userMapper.insert(user);
    }

    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public Integer insertUser1(User user){
        return userMapper.insert(user);
    }

    /**
     * 修改用户
     * @param user
     */
    public Integer updateUser(User user) {

        return userMapper.updateById(user);
    }

    /**
     * 删除用户
     * @param id
     */
    public Integer deleteUser(Integer id) {
        return userMapper.deleteById(id);
    }

}
