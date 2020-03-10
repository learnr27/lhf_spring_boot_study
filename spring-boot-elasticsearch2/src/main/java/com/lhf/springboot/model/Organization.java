package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Organization
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/6 16:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Organization {

    private Long id;
    private String name;
    private String address;
}
