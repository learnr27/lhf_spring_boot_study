package com.lhf.springboot.mapper;

import com.lhf.springboot.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: StudentMapper
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 16:41
 */

@Mapper
public interface StudentMapper {


    @Insert("insert into t_student(sno,name,sex) values(#{sno},#{name},#{sex})")
    void addStudent(Student student);


    @Update("update t_student set name=#{name},sex=#{sex} where sno=#{sno}")
    void updateStudent(Student student);


    @Delete("delete from t_student where sno=#{sno}")
    void deleteById(String sno);


    @Select("select * from t_student where sno=#{sno}")
    @Results(id = "student", value = {@Result(property = "sno", column = "sno",javaType = String.class),
                                      @Result(property = "name", column = "name", javaType = String.class),
                                      @Result(property = "sex", column = "sex", javaType = String.class)})
    Student findById(String sno);

    @Select("select * from t_student")
    List<Student> findAll();
}
