package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName: Employee
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/6 16:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "sample", type = "employee")  //对象存储为文档,为Elasticsearch设置目标索引的名称，类型和ID
public class Employee {

    @Id
    private Long id;
    @Field(type = FieldType.Object)  //@Field注解配置映射
    private Organization organization;
    @Field(type = FieldType.Object)
    private Department department;
    private String name;
    private int age;
    private String position;
}
