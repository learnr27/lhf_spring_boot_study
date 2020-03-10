package com.lhf.springboot.repository;

import com.lhf.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Author: liuhefei
 * @Description: jps接口
 * @Date: 2019/5/29 14:40
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findById(Integer id);
}
