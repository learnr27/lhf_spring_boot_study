package com.lhf.springboot.repository;

import com.lhf.springboot.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EmployeeRepository
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/6 16:07
 */
@Repository
public interface EmployeeRepository extends ElasticsearchCrudRepository<Employee, Long> {

    List<Employee> findByOrganizationName(String name);
    List<Employee> findByName(String name);

}
