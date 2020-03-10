package com.lhf.springboot.mapper;

import com.lhf.springboot.enums.UserSexEnum;
import com.lhf.springboot.model.Users;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: UsersMapperTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/4 17:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testInsert() throws Exception {
        usersMapper.insert(new Users("aa", "a123456", UserSexEnum.MAN));
        usersMapper.insert(new Users("bb", "b123456", UserSexEnum.WOMAN));
        usersMapper.insert(new Users("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, usersMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<Users> users = usersMapper.getAll();
        if(users==null || users.size()==0){
            System.out.println("is null");
        }else{
            System.out.println(users.toString());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Users user = usersMapper.getOne(6l);
        System.out.println(user.toString());
        user.setNickName("neo");
        usersMapper.update(user);
        Assert.assertTrue(("neo".equals(usersMapper.getOne(6l).getNickName())));
    }
}
