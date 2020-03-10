package com.lhf.springboot.service.impl;

import com.lhf.springboot.entity.Student;
import com.lhf.springboot.mapper.StudentMapper;
import com.lhf.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: StudentServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 16:49
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteById(String sno) {
        studentMapper.deleteById(sno);
    }

    @Override
    public Student findById(String sno) {
        return studentMapper.findById(sno);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
