package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Book
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/10 15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {


    private static final long serialVersionUID=1L;
    private Integer id;
    private String bookName;
    private String bookAuthor;
    private Double bookPrice;
    private String remark;

}
