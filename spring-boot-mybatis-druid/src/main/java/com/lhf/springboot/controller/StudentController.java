package com.lhf.springboot.controller;

import com.lhf.springboot.entity.Student;
import com.lhf.springboot.mapper.StudentMapper;
import com.lhf.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: StudentController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 16:50
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/findById/{sno}")
    public Student findBuId(@PathVariable("sno")String sno){
        return studentService.findById(sno);
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @PostMapping("/update")
    public void update(@RequestBody Student student){
        studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteById/{sno}")
    public void deleteById(@PathVariable("sno") String sno){
        studentService.deleteById(sno);
    }

}
