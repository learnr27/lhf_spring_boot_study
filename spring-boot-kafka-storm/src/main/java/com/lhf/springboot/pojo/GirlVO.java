package com.lhf.springboot.pojo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: GirlVO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:15
 */
@Data
public class GirlVO {

    private Integer total;
    private List<Girl> data;
    private Integer pageSize;
}
