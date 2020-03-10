package com.lhf.springboot.service;

import com.lhf.springboot.entity.Student;

import java.util.List;

/**
 * @ClassName: StudentService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 16:47
 */
public interface StudentService {
    void addStudent(Student student);


    void updateStudent(Student student);


    void deleteById(String sno);


    Student findById(String sno);


    List<Student> findAll();
}
