package com.lhf.springboot.mapper.test2;

import com.lhf.springboot.enums.UserSexEnum;
import com.lhf.springboot.model.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: User2Mapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/4 11:48
 */
public interface User2Mapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    List<Users> getAll();

    @Select("SELECT * FROM users WHERE id = #{id} ")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    Users getOne(Long id);

    @Insert("INSERT INTO users(userName, passWord, user_sex, nick_name) VALUES(#{userName}, #{passWord}, #{userSex}, #{nickName})")
    void insert(Users users);

    @Update("UPDATE users SET userName = #{userName}, nick_name = #{nickName} WHERE id = #{id} ")
    void update(Users users);

    @Delete("DELETE FROM users WHERE id = #{id} ")
    void delete(Long id);
}
