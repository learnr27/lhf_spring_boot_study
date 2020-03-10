package com.lhf.springboot.dao.master;

import com.lhf.springboot.dao.BaseDao;
import com.lhf.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: StudentDao
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/26 17:22
 */
@Mapper
public interface UserDao extends BaseDao<User> {

}
