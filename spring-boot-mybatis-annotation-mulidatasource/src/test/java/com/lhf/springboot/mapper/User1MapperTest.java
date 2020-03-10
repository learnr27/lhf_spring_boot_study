package com.lhf.springboot.mapper;

import com.lhf.springboot.enums.UserSexEnum;
import com.lhf.springboot.mapper.test1.User1Mapper;
import com.lhf.springboot.model.Users;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: User1MapperTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/4 14:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class User1MapperTest {

    @Autowired
    private User1Mapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new Users("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new Users("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new Users("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<Users> users = userMapper.getAll();
        if(users==null || users.size()==0){
            System.out.println("is null");
        }else{
            System.out.println(users.size());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Users user = userMapper.getOne(4l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
        Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
    }

}
