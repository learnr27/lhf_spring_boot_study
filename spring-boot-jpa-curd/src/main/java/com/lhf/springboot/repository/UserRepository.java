package com.lhf.springboot.repository;

import com.lhf.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 16:57
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Integer id);

    void deleteById(Integer id);
}
