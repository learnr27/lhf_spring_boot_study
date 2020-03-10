package com.lhf.springboot.controller;


import com.lhf.springboot.pojo.Student;
import com.lhf.springboot.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName: StudentRestController
 * @Author: liuhefei
 * @Description: 学生控制层
 * @Date: 2019/7/26 10:40
 */
@Api(value = "学生Api接口", tags = "学生", description = "学生Api")
@RestController
@RequestMapping(value = "/api")
public class StudentRestController {
	@Autowired
    private StudentService service;

    @ApiOperation("添加学生")
	@RequestMapping(value = "/student", method = RequestMethod.POST)
    public boolean addStudent(@RequestBody Student student) {
    	System.out.println("开始新增...");
        return service.insert(student);
    }

    @ApiOperation("更新学生")
	@RequestMapping(value = "/student", method = RequestMethod.PUT)
    public boolean updateStudent(@RequestBody Student student) {
    	System.out.println("开始更新...");
        return service.update(student);
    }

    @ApiOperation("删除学生")
	@RequestMapping(value = "/student", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody Student student) {
    	System.out.println("开始删除...");
        return service.delete(student);
    }

    @ApiOperation("查询学生")
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> findByStudent(Student student) {
    	System.out.println("开始查询...");
        return service.findByListEntity(student);
    }
}
