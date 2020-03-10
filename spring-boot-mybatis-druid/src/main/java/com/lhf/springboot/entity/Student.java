package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Student
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/19 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer sid;

    private String sno;

    private String name;

    private String sex;

}
