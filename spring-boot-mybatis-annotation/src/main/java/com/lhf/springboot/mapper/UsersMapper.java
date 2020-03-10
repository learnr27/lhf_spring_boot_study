package com.lhf.springboot.mapper;

import com.lhf.springboot.enums.UserSexEnum;
import com.lhf.springboot.model.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: UsersMapper
 * @Author: liuhefei
 * @Description: Mybatis基于注解实现
 * @Date: 2019/6/3 17:42
 */
public interface UsersMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<Users> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    Users getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex}) ")
    void insert(Users users);

    @Update("UPDATE users SET userName = #{userName}, nick_name = #{nickName} WHERE id = #{id} ")
    void update(Users users);

    @Delete("DELETE FROM users WHERE id = #{id} ")
    void delete(Long id);
}
