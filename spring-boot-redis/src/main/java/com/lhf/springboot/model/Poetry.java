package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Poetry
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poetry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String redisKey;//redis中的key

    private String title;

    private String[] paragraphs;

    private String author;

    private String rhythmic;

    private String[] notes;
}
