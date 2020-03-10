package com.lhf.springboot;


import com.lhf.springboot.model.Department;
import com.lhf.springboot.model.Employee;
import com.lhf.springboot.model.Organization;
import com.lhf.springboot.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: SampleDataSet
 * @Author: liuhefei
 * @Description: 初始化数据
 * 如果您想在一步就插入许多文档，那么您一定要使用Bulk API。bulk API使得在单个API调用中执行许多索引/删除操作成为可能。这可以大大提高索引速度。可以使用Spring Data ElasticsearchTemplate bean执行批量操作。它在Spring Boot上也可以自动配置。
 * Template提供了bulkIndex方法，该方法将索引查询列表作为输入参数
 * @Date: 2019/9/6 16:04
 */
public class SampleDataSet {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataSet.class);
    private static final String INDEX_NAME = "sample";
    private static final String INDEX_TYPE = "employee";

    @Autowired
    EmployeeRepository repository;

    @Autowired
    ElasticsearchTemplate template;

    @PostConstruct
    public void init(){
        for(int i = 0; i < 10000; i++){
            bulk(i);
        }
    }

    public void bulk(int ii) {
        try {
            if (!template.indexExists(INDEX_NAME)) {
                template.createIndex(INDEX_NAME);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<IndexQuery> queries = new ArrayList<>();
            List<Employee> employees = employees();
            for (Employee employee : employees) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(employee.getId().toString());
                indexQuery.setSource(mapper.writeValueAsString(employee));
                indexQuery.setIndexName(INDEX_NAME);
                indexQuery.setType(INDEX_TYPE);
                queries.add(indexQuery);
            }
            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh(INDEX_NAME);
            logger.info("BulkIndex completed: {}", ii);
        } catch (Exception e) {
            logger.error("Error bulk index", e);
        }
    }

    private List<Employee> employees() {
        List<Employee> employees = new ArrayList<>();
        int id = (int) repository.count();
        logger.info("Starting from id: {}", id);
        for (int i = id; i < 10000 + id; i++) {
            Random r = new Random();
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName("John Smith" + r.nextInt(1000000));
            employee.setAge(r.nextInt(100));
            employee.setPosition("Developer");
            int departmentId = r.nextInt(5000);
            employee.setDepartment(new Department((long) departmentId, "TestD" + departmentId));
            int organizationId = departmentId % 100;
            employee.setOrganization(new Organization((long) organizationId, "TestO" + organizationId, "Test Street No. " + organizationId));
            employees.add(employee);
        }
        return employees;
    }

}
