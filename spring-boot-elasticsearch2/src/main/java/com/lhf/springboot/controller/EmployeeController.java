package com.lhf.springboot.controller;

import com.lhf.springboot.model.Employee;
import com.lhf.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: EmployeeController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/6 17:33
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping("/{name}")
    public List<Employee> findByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/organization/{organizationName}")
    public List<Employee> findByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return repository.findByOrganizationName(organizationName);
    }

}
