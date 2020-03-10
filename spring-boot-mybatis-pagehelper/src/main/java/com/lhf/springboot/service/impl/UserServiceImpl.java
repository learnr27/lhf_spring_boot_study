package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.BaseDao;
import com.lhf.springboot.dao.master.UserDao;
import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Author: liuhefei
 * @Description: 用户接口实现类
 * @Date: 2019/7/26 11:32
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	protected BaseDao<User> getMapper() {
		return this.userDao;
	}

}
