package com.lhf.springboot.benchmark.json.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FullName
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 11:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {

    private String firstName;
    private String middleName;
    private String lastName;
}
