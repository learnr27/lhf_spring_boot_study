package com.lhf.springboot.dao;

import com.lhf.springboot.pojo.Girl;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: GirlDao
 * @Author: liuhefei
 * @Description: 女孩数据接口
 * @Date: 2019/8/2 10:36
 */
@Mapper
public interface GirlDao {

    @Insert("insert into girl(id, name, age, height, weight, cup_size) values(#{id}, #{name}, #{age}, #{height}, #{weight}, #{cupSize})")
    public Integer insert(Girl girl);

    @Update("update girl set name=#{name}, age=#{age}, height=#{height}, weight=#{weight}, cup_size=#{cupSize} where id=#{id}")
    public Integer update(Girl girl);

    @Select("select * from girl")
    public List<Girl> fingByAll();

    @Select("select * from girl where id=#{id}")
    public Girl findByGirlId(Integer id);

    @Delete("delete from girl where id=#{id}")
    public void delete(Integer id);
}
