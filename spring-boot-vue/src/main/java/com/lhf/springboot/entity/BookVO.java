package com.lhf.springboot.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: BookVO
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:51
 */
@Data
public class BookVO {

    private Integer total;
    private List<Book> data;
    private Integer pageSize;
}
