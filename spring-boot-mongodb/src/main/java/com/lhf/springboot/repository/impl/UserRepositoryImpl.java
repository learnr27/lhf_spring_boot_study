package com.lhf.springboot.repository.impl;

import com.lhf.springboot.model.User;
import com.lhf.springboot.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


/**
 * @ClassName: UserRepositoryImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/30 9:34
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    private final static Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void saveUser(User user) {
        logger.info("添加用户：{}", user);
        mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询对象
     * @param name
     * @return
     */
    @Override
    public User findUserByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query, User.class);
        logger.info("根据用户名查询用户：{}", user);
        return user;
    }

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    @Override
    public long updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("name", user.getName())
                                    .set("age", user.getAge())
                                    .set("phone", user.getPhone())
                                    .set("email", user.getEmail());
        //更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        logger.info("更新用户数据：{}", result);
        //更新查询返回结果集的所有
        //mongoTemplate.updateMulti(query, update, User.class);
        if(result != null){
            return result.getMatchedCount();
        }else {
            return 0;
        }
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUserById(int id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
        logger.info("id为{}", id, "的用户删除成功！");
    }
}
